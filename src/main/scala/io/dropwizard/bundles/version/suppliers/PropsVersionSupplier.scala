package io.dropwizard.bundles.version.suppliers

import java.util.Properties

import io.dropwizard.bundles.version.{VersionInfo, VersionSupplier}

class PropsVersionSupplier extends VersionSupplier {

  var infos: VersionInfo = null

  override def info: VersionInfo = {
    if(infos != null ) infos else versionInfo()
  }

  private def versionInfo() = {
    val pom = load("pom.properties")
    val result = VersionInfo(pom.getProperty("groupId"), pom.getProperty("artifactId"), pom.getProperty("version"))
    infos = result
    result
  }

  private def load(file: String): Properties = {
    val pom: Properties = new Properties()
    val stream = this.getClass.getClassLoader.getResourceAsStream("pom.properties")
    pom.load(stream)
    stream.close()
    pom
  }
}
