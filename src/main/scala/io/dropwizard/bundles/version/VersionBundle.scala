package io.dropwizard.bundles.version

class VersionBundle(supplier: VersionSupplier) extends Bundle {

  val url: String = "/version"

  override def initialize(bootstrap: Bootstrap[_]): Unit = {/*DO NOTHING*/}

  override def run(environment: Environment): Unit = {
    addEndpoint(environment, url) {
      new VersionServlet(supplier)
    }
  }
}
