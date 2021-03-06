import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val karibudsl_version = "0.6.3"
val vaadin10_version = "13.0.5"


plugins {
  kotlin("jvm") version "1.3.40"
  id("org.gretty") version "2.3.1"
  war
  id("com.devsoap.vaadin-flow") version "1.2"
  //id("org.springframework.boot") version "2.1.5.RELEASE"
  //id("io.spring.dependency-management") version "1.0.6.RELEASE"
  //id("org.jetbrains.kotlin.plugin.spring") version "1.3.40"
}

vaadin {
  version = "13.0.9"
  this.setUnsupportedVersion(true)
}

defaultTasks("clean", "build")

repositories {
  jcenter()
  maven(url = "http://maven.vaadin.com/vaadin-addons")
}

gretty {
  contextPath = "/"
  servletContainer = "jetty9.4"
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events("passed", "skipped", "failed")
  }
}
val staging by configurations.creating


dependencies {
  compile(enforcedPlatform("com.vaadin:vaadin-bom:$vaadin10_version"))
  //compile("com.vaadin:vaadin-spring:$vaadin10_version")
  // Karibu-DSL dependency, includes Vaadin
  compile("com.github.mvysny.karibudsl:karibu-dsl-v10:$karibudsl_version")
  providedCompile("javax.servlet:javax.servlet-api:3.1.0")
  // logging
  // currently we are logging through the SLF4J API to LogBack. See src/main/resources/logback.xml file for the logger configuration
  compile("ch.qos.logback:logback-classic:1.2.3")
  compile("org.slf4j:slf4j-api:1.7.25")

  compile(kotlin("stdlib-jdk8"))
  compile(kotlin("reflect"))
  // test support
  testCompile("com.github.mvysny.kaributesting:karibu-testing-v10:1.1.6")
  testCompile("com.github.mvysny.dynatest:dynatest-engine:0.15")
  // heroku app runner
  staging("com.github.jsimone:webapp-runner-main:9.0.17.0")

  compile("mysql:mysql-connector-java:5.1.47")
  compile("org.sql2o:sql2o:1.5.4")
  compile("com.jolbox:bonecp:0.8.0.RELEASE")

  compile("org.cups4j:cups4j:0.7.1")
  compile("org.glassfish.jersey.core:jersey-client:2.27")
  compile("org.glassfish.jersey.media:jersey-media-multipart:2.27")
  compile("org.glassfish.jersey.inject:jersey-hk2:2.27")
  compile("org.imgscalr:imgscalr-lib:4.2")

  compile("com.github.appreciated:card:0.9.4")
  compile("org.claspina:confirm-dialog:1.0.0")
  //implementation("org.springframework.boot:spring-boot-starter-web")
  //providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")


  // Kotlin reflection.
  compile(kotlin("test"))
  compile(kotlin("test-junit"))


  // JUnit 5
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.2.0")
  testRuntime("org.junit.platform:junit-platform-console:1.2.0")

  // Kotlintest
  testCompile("io.kotlintest:kotlintest-core:3.1.0-RC2")
  testCompile("io.kotlintest:kotlintest-assertions:3.1.0-RC2")
  testCompile("io.kotlintest:kotlintest-runner-junit5:3.1.0-RC2")
  compile ("com.willowtreeapps.assertk:assertk:0.17")
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}

