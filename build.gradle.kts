import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val karibudsl_version = "0.6.3"
val vaadin10_version = "13.0.5"

plugins {
  kotlin("jvm") version "1.3.31"
  id("org.gretty") version "2.3.1"
  war
  id("com.devsoap.vaadin-flow") version "1.1.2"
}

vaadin {
  version = "13.0.5"
  setUnsupportedVersion(true)
}

defaultTasks("clean", "build")

repositories {
  jcenter()
}

gretty {
  contextPath = "/"
  servletContainer = "jetty9.4"
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    // to see the exceptions of failed tests in Travis-CI console.
    exceptionFormat = TestExceptionFormat.FULL
  }
}
val staging by configurations.creating

dependencies {
  compile(enforcedPlatform("com.vaadin:vaadin-bom:$vaadin10_version"))
  // Karibu-DSL dependency, includes Vaadin
  compile("com.github.mvysny.karibudsl:karibu-dsl-v10:$karibudsl_version")
  providedCompile("javax.servlet:javax.servlet-api:3.1.0")
  // logging
  // currently we are logging through the SLF4J API to LogBack. See src/main/resources/logback.xml file for the logger configuration
  compile("ch.qos.logback:logback-classic:1.2.3")
  compile("org.slf4j:slf4j-api:1.7.25")

  compile(kotlin("stdlib-jdk8"))
  // test support
  testCompile("com.github.mvysny.kaributesting:karibu-testing-v10:1.1.6")
  testCompile("com.github.mvysny.dynatest:dynatest-engine:0.15")
  // heroku app runner
  staging("com.github.jsimone:webapp-runner-main:9.0.17.0")
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}

