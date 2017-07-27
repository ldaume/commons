import aether.AetherKeys._

// Project name (artifact name in Maven)
name := """commons"""

// orgnization name (e.g., the package name of the project)
organization := "software.reinvent"

//version := "0.4.0-SNAPSHOT"
version := "0.3.4"

scalaVersion := "2.12.2"

// project description
description := "Common dependencies and utils for projects."

// Enables publishing to maven repo
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }

publishTo := {
  val nexus = "https://maven.reinvent-software.de/nexus/"
  if (version.value.trim.endsWith("SNAPSHOT")) {
    Some("snapshots" at nexus + "content/repositories/snapshots")
  } else {
    Some("releases" at nexus + "content/repositories/releases")
  }
}

overridePublishBothSettings

//credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
credentials += Credentials(Path.userHome / ".ivy2" / ".credentials.deploy")

// Do not append Scala versions to the generated artifacts
crossPaths := false

// This forbids including Scala related libraries into the dependency
autoScalaLibrary := false


resolvers ++= Seq(
  Resolver.mavenLocal,
  "ReInvent Software OSS" at "https://maven.reinvent-software.de/nexus/content/groups/public"
)


libraryDependencies ++= Seq(


  "com.google.inject" % "guice" % "4.1.0",

  // Commons
  "org.apache.commons" % "commons-lang3" % "3.6",
  "com.google.guava" % "guava" % "22.0",
  "org.apache.commons" % "commons-collections4" % "4.1",
  "commons-io" % "commons-io" % "2.5",
  "com.typesafe" % "config" % "1.3.1",
  "org.unbescape" % "unbescape" % "1.1.5.RELEASE",
  "com.github.rholder" % "guava-retrying" % "2.0.0" exclude("com.google.guava", "guava"),

  // READABILITY
  "com.github.mfornos" % "humanize-slim" % "1.2.2" exclude("com.google.guava", "guava"),


  // LOGGING
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "ch.qos.logback" % "logback-core" % "1.2.3",


  // TEST
  "org.assertj" % "assertj-core" % "3.8.0" % "test",
  "org.assertj" % "assertj-guava" % "3.1.0" % "test" exclude("com.google.guava", "guava"),
  "com.novocode" % "junit-interface" % "0.11" % "test->default",
  "org.jukito" % "jukito" % "1.5" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")

dependencyUpdatesFailBuild := true
