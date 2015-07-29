package io.dropwizard.bundles.version.suppliers

import java.util.Properties

import io.dropwizard.bundles.version.{VersionInfo, VersionSupplier}

class PropsVersionSupplier extends VersionSupplier {

  override def info: VersionInfo = {
    val pom = load("pom.properties")
    VersionInfo(pom.getProperty("groupId"), pom.getProperty("artifactId"), pom.getProperty("version"))
  }

  private def load(file: String): Properties = {
    val pom: Properties = new Properties()
    val stream = this.getClass.getClassLoader.getResourceAsStream("pom.properties")
    pom.load(stream)
    stream.close()
    pom
  }
}
