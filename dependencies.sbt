
libraryDependencies ++= {
  val mavenVersion = "3.3.9"
  val aetherVersion = "1.1.0"
  Seq(
    "com.google.inject" % "guice" % "4.2.0",

    // Commons
    "org.apache.commons" % "commons-lang3" % "3.7",
    "com.google.guava" % "guava" % "24.0-jre",
    "org.apache.commons" % "commons-collections4" % "4.1",
    "commons-io" % "commons-io" % "2.6",
    "com.typesafe" % "config" % "1.3.3",
    "org.unbescape" % "unbescape" % "1.1.5.RELEASE",
    "com.github.rholder" % "guava-retrying" % "2.0.0" exclude("com.google.guava", "guava"),

    // READABILITY
    "com.github.mfornos" % "humanize-slim" % "1.2.2" exclude("com.google.guava", "guava"),


    // LOGGING
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "ch.qos.logback" % "logback-core" % "1.2.3",


    // TEST
    "org.assertj" % "assertj-core" % "3.9.1" % "test",
    "org.assertj" % "assertj-guava" % "3.1.0" % "test" exclude("com.google.guava", "guava"),
    "com.novocode" % "junit-interface" % "0.11" % "test->default",
    "org.jukito" % "jukito" % "1.5" % "test"
  )
}

dependencyUpdatesFailBuild := true
