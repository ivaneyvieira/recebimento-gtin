package br.com.pintos.recebimento_gtin.viewmodel

import br.com.pintos.recebimento_gtin.model.LoginUser

object SecurityUtils {
  var user: LoginUser? = null

  fun isUserLoggedIn(): Boolean {
    return user != null
  }

  fun configDB(){
    val home = System.getenv("HOME")
    val fileName = System.getenv("EBEAN_PROPS") ?: "$home/ebean.saci.properties"
    println("Salvando configurações $fileName")
    System.setProperty("ebean.props.file", fileName)
  }

  fun logout() {
    user = null
  }
}