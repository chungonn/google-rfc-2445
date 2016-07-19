name := "google-rfc-2445"

organization := "com.greenfossil"

version := "1.0.0"

scalaVersion := "2.11.8"

lazy val rfc2445 = (project in file("."))

resolvers ++= Seq(
  "GF Release" at "http://www.greenfossil.com:8081/nexus/content/groups/public/",
  "GF Snapshot" at "http://www.greenfossil.com:8081/nexus/content/groups/public-snapshots/",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.9.4" % "provided",
  "org.joda" % "joda-convert" % "1.8.1" % "provided",
  "org.apache.logging.log4j" % "log4j-api" % "2.6.1" % "provided",
  "org.apache.logging.log4j" % "log4j-core" % "2.6.1" % "provided"
)
