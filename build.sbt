import sbt.Keys.version

// Project name (artifact name in Maven)
name := """commons"""

// orgnization name (e.g., the package name of the project)
organization := "software.reinvent"

//version := "0.4.0-SNAPSHOT"
//version in ThisBuild := "0.4.0-SNAPSHOT"
version := "0.3.10"
version in ThisBuild := "0.3.10"

scalaVersion := "2.12.4"

// project description
description := "Common dependencies and utils for projects."

scalacOptions in Test ++= Seq("-Yrangepos")
