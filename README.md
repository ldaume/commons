| Build Status | Dependencies UpToDate | Latest Version | License |
|:------------:|:---------------------:|:-------:|:-------:|
| [![Build Status](https://ci.reinvent-software.de/buildStatus/icon?job=Commons-Build)](https://ci.reinvent-software.de/job/Commons-Build) | [![Dependencies UpToDate](https://ci.reinvent-software.de/buildStatus/icon?job=Commons-DependencyCheck)](https://ci.reinvent-software.de/job/Commons-DependencyCheck) | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/software.reinvent/commons/badge.svg)](https://maven-badges.herokuapp.com/maven-central/software.reinvent/commons) | [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) |



Commons
==========

<!-- TOC depthFrom:1 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [Abstract](#abstract)
- [Usage](#usage)
	- [Repo](#repo)
		- [SBT](#sbt)
		- [Maven](#maven)
- [Overview](#overview)
	- [software.reinvent.commons.config](#softwarereinventcommonsconfig)
		- [Cached Config](#cached-config)
		- [Guice Provider](#guice-provider)
	- [software.reinvent.commons.jvm](#softwarereinventcommonsjvm)
	- [software.reinvent.commons.log](#softwarereinventcommonslog)
	- [software.reinvent.commons.utils](#softwarereinventcommonsutils)
	- [software.reinvent.commons.test](#softwarereinventcommonstest)

<!-- /TOC -->

# Abstract
Common dependencies and utils for projects.

# Usage

## Repo
Just add the following [maven central](https://mvnrepository.com/artifact/software.reinvent/commons) dependency.

### SBT
Add dependency to `build.sbt`.
```bash
libraryDependencies += "software.reinvent" % "commons" % "0.3.4"
```

### Maven
```xml
<dependency>
    <groupId>software.reinvent</groupId>
    <artifactId>commons</artifactId>
    <version>0.3.4</version>
</dependency
```

# Overview

## software.reinvent.commons.config
The [typesafe config](https://github.com/typesafehub/config) is great for any configuration. The convenience method
`software.reinvent.commons.config.ConfigLoader.load()`, loads the following (first-listed are higher priority):

- system properties
- file path from system property `provided.config`
- `{username}.conf` in classpath resources
- `application.conf` (all resources on classpath with this name)
- `application.json` (all resources on classpath with this name)
- `application.properties` (all resources on classpath with this name)
- `reference.conf` (all resources on classpath with this name)

### Cached Config
A cached version is available via `software.reinvent.commons.config.CachedConfig.load()` provided with a [LoadingCache](https://github.com/google/guava/wiki/CachesExplained).

### Guice Provider
For any [Guice](https://github.com/google/guice) powered applications, one would love `software.reinvent.commons.config.ConfigProvider` or `software.reinvent.commons.config.CachedConfigProvider`

## software.reinvent.commons.jvm
The `JvmUtil` comes from my former colleague [Daniel Galán y Martins](https://github.com/galan/commons#degalancommonsutil) and provides access to information about the currently running JVM and some process-control (eg. fluent Termination of JVM, ShutdownHook methods).

## software.reinvent.commons.log
Provides a injectable [logback](http://logback.qos.ch/) implementation for [slf4j](http://www.slf4j.org/) which can be used for every logging.

Just bind the `Slf4jTypeListener` in a Guice module:
```java
import static com.google.inject.matcher.Matchers.any;

...

@Override
protected void configure() {
  bindListener(any(), new Slf4jTypeListener());
}
```

And use the logger in any injected class like this:
```java
import org.slf4j.Logger;
import software.reinvent.log.InjectLogger;

...

@InjectLogger
private Logger log;
```

`log.info("{}st log.", 1)` will log something like `10:21:17,914 INFO [ForkJoinPool-1-worker-3] application: Class::Method:#Line - 1st log.`

## software.reinvent.commons.utils
`Comparators` - Provides a null friendly version string comparator for dot-separated tuple like v2.1.10 or 0.1.12.5.

## software.reinvent.commons.test
`ConfigTestUtils` - Provides some methods to add, replace or remove entries in the immutable typesafe config.
