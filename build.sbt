name := "google-rfc-2445"

organization := "com.greenfossil"

version := "1.1.0"


//Do not append scala versions to the generated artifacts
crossPaths := false

//This forbids including scala related libraries into the dependency
autoScalaLibrary := false

lazy val rfc2445 = (project in file(".")).settings(
  publishArtifact in (Compile, packageDoc) := false,
  publishArtifact in packageDoc := false,
  sources in (Compile, doc) := Seq.empty
)

publishMavenStyle := true

pomIncludeRepository := { _ => false }

pomExtra :=
  <licenses>
    <license>
      <name>Greenfossil License</name>
      <url>http://www.opensource.org/licenses/bsd-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>

resolvers ++= Seq(
  "GF Release" at "http://www.greenfossil.com:8081/nexus/content/groups/public/",
  "GF Snapshot" at "http://www.greenfossil.com:8081/nexus/content/groups/public-snapshots/",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
)
