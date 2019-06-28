package rest

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean

class WebsocketSourceConfiguration {
  @Bean
  fun servletWebServerFactory():ServletWebServerFactory{
    return TomcatServletWebServerFactory()
  }
}