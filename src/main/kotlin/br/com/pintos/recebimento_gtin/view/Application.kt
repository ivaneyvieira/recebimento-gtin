package br.com.pintos.recebimento_gtin.view

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.boot.builder.SpringApplicationBuilder



@SpringBootApplication
open class Application: SpringBootServletInitializer(){
  override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
    return application.sources(Application::class.java)
  }
}

fun main(args: Array<String>) {
  SpringApplication.run(Application::class.java, *args)
}
