plugins {
  kotlin("jvm")
  id("org.springframework.boot")
  id("io.spring.dependency-management")
  id("org.jetbrains.kotlin.plugin.spring")
}

repositories {
  jcenter()
  maven(url = "http://maven.vaadin.com/vaadin-addons")
}

dependencies {
  implementation(project(":"))
  compile(kotlin("stdlib-jdk8"))
  compile("mysql:mysql-connector-java:5.1.47")
  implementation("org.springframework.boot:spring-boot-starter-web")
  //providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
}

