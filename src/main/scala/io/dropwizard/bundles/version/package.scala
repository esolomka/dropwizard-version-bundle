package io.dropwizard.bundles

import javax.servlet.http.HttpServlet

package object version {

  type Environment = io.dropwizard.setup.Environment
  type Bootstrap[T <: io.dropwizard.Configuration] = io.dropwizard.setup.Bootstrap[T]
  type Bundle = io.dropwizard.Bundle

  def addEndpoint(environment : Environment, url : String)(servlet: HttpServlet) = {
    environment.servlets().addServlet("version", servlet).addMapping(url)
    environment.admin().addServlet("version", servlet).addMapping(url)
  }

}
