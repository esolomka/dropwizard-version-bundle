name := "dropwizard-version-bundle"

val baseVersion = "0.8"

version := s"$baseVersion-SNAPSHOT"

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

val dropwizard = baseVersion match {
  case "0.6" => "com.yammer.dropwizard" % "dropwizard-core" % s"$baseVersion.2"
  case _ => "io.dropwizard" % "dropwizard-core" % s"$baseVersion.2"
}


libraryDependencies ++= List(
  dropwizard,
  "org.mockito" % "mockito-core" % "1.10.8" % "test"
)

sourceGenerators in Compile <+= (sourceManaged in Compile, version) map { (d, v) =>
  val file = d / "package.scala"
  IO.write(file, """package io.dropwizard.bundles
                   |
                   |import javax.servlet.http.HttpServlet
                   |
                   |package object version {
                   |
                   |  type Environment = io.dropwizard.setup.Environment
                   |  type Bootstrap[T <: io.dropwizard.Configuration] = io.dropwizard.setup.Bootstrap[T]
                   |  type Bundle = io.dropwizard.Bundle
                   |
                   |  def addEndpoint(environment : Environment, url : String)(servlet: HttpServlet) = {
                   |    environment.servlets().addServlet("version", servlet).addMapping(url)
                   |    environment.admin().addServlet("version", servlet).addMapping(url)
                   |  }
                   |
                   |}""".stripMargin.format(v))
  Seq(file)
}