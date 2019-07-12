package br.com.pintos.framework.utils

import assertk.assertThat
import assertk.assertions.isBetween
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class StrUtilsKtTest {
  val gtinsOk = listOf(
    "7909408464922",
    "7909408468098",
    "7909408468081",
    "7909408468074",
    "7909408468067",
    "7909408468104",
    "7909408468043",
    "7909408468036",
    "7909408462201",
    "7909408453698",
    "7909408462218",
    "7909408462225",
    "7909408462232",
    "7909408462249",
    "7909408462256",
    "7909408462263",
    "7909408468050")
  val gtinErro = listOf(
    "7909418464922",
    "7909418468098",
    "7909418468081",
    "7909418468074",
    "7909418468067",
    "7909418468104",
    "7909418468043",
    "7909418468036",
    "7909418462201",
    "7909418453698",
    "7909418462218",
    "7909418462225",
    "7909418462232",
    "7909418462249",
    "7909418462256",
    "790948462263",
    "790941108468050"
                       )
/*
  @Test
  fun isValidBarCodeEANOk() {
    gtinsOk.forEach {gtin ->
      val value = gtin.isValidBarCodeEAN()
      println("$gtin is $value")
      assertThat(value).isEqualTo(true)
    }
  }
  @Test
  fun isValidBarCodeEANErro() {
    gtinErro.forEach {gtin ->
      val value = gtin.isValidBarCodeEAN()
      println("$gtin is $value")
      assertThat(value).isEqualTo(false)
    }
  }*/
}