name := "google-rfc-2445"

organization := "com.greenfossil"

version := "1.1.0"


//Do not append scala versions to the generated artifacts
crossPaths := false

//This forbids including scala related libraries into the dependency
autoScalaLibrary := false

lazy val rfc2445 = (project in file("."))

resolvers ++= Seq(
  "GF Release" at "http://www.greenfossil.com:8081/nexus/content/groups/public/",
  "GF Snapshot" at "http://www.greenfossil.com:8081/nexus/content/groups/public-snapshots/",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
)
