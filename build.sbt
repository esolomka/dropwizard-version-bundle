name := "dropwizard-version-bundle"

version := "0.8-SNAPSHOT"

organization := "fr.novapost.dropwizard-bundles"

scalaVersion := "2.11.7"

scalacOptions += "-feature"

resolvers ++= Seq(
  "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
  "Novapost repo" at "http://nibbler:9081/nexus/content/repositories/nova",
  "Novapost snaps repo" at "http://nibbler:9081/nexus/content/repositories/snapshots",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)


publishTo := {
  val nexus = "http://nibbler:9081/nexus/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "content/repositories/nova")
}


libraryDependencies ++= List(
  "io.dropwizard" % "dropwizard-core" % "0.8.2",
  "org.mockito" % "mockito-core" % "1.10.8" % "test"
)