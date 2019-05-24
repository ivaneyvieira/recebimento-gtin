package br.com.pintos.recebimento_gtin.viewmodel

import br.com.pintos.recebimento_gtin.model.LoginUser

class LoginViewModel {
  var username: String = ""
  var password: String = ""
  var loginOk: Boolean = false
  private val usuarios = LoginUser.usuarios

  fun processLogin() {
    val user = usuarios.find {it.login == username && it.senha == password}
    loginOk = user != null
  }

  fun logout() {

  }
}