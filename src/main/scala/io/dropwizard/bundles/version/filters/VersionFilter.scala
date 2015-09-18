package io.dropwizard.bundles.version.filters

import javax.servlet._
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

class VersionFilter(val version: String) extends Filter {

  override def init(filterConfig: FilterConfig): Unit = {}

  override def destroy(): Unit = {}

  override def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain): Unit = {
    if (request.isInstanceOf[HttpServletRequest]) {
      response.asInstanceOf[HttpServletResponse].addHeader("X-Version", version)
      chain.doFilter(request, response)
    }
  }
}

