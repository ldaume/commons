[![Build Status](https://ci.reinvent-software.de/buildStatus/icon?job=Commons-Build)](https://ci.reinvent-software.de/job/Commons-Build)

Commons
==========

# Abstract
Common dependencies and utils for projects.

# Usage

## Repo
Add maven repo to `build.sbt`.
```bash
resolvers ++= Seq(
  Resolver.mavenLocal,
  "ReInvent Software OSS" at "https://maven.reinvent-software.de/nexus/content/groups/public"
)
```

## Dependency
`libraryDependencies += "software.reinvent" % "commons" % "0.1.0"`

