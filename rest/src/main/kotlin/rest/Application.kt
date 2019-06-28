package rest

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application

fun configDB() {
  val home = System.getenv("HOME")
  val fileName = System.getenv("EBEAN_PROPS") ?: "$home/ebean.saci.properties"
  println("Salvando configurações $fileName")
  System.setProperty("ebean.props.file", fileName)
}

fun main(args: Array<String>) {
  configDB()
  SpringApplication.run(Application::class.java, *args)
}