package br.com.pintos.recebimento_gtin.viewmodel

import br.com.pintos.recebimento_gtin.model.LoginUser

object SecurityUtils {
  var user: LoginUser? = null
  val isLogged
    get() = user != null

  fun login(user: LoginUser) {
    this.user = user
  }

  fun logout() {
    user = null
  }
}