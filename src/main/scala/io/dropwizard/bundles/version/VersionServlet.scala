package io.dropwizard.bundles.version

import javax.servlet.ServletOutputStream
import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}

class VersionServlet(supplier : VersionSupplier) extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit =  {
    val out: ServletOutputStream = resp.getOutputStream
    val info = supplier.info
    resp.setStatus(200)
    resp.setContentType("application/json")
    out.write(s"""{"version": "${info.version}", "name": "${info.artifact}"}""".getBytes("UTF-8"))
    out.close()
  }
}
