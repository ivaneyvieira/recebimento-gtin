package br.com.pintos.recebimento_gtin.viewmodel

import br.com.pintos.recebimento_gtin.model.LoginUser

class LoginViewModel {
  var username: String = ""
  var password: String = ""
  val loginOk
    get() = SecurityUtils.isLogged
  private val usuarios = LoginUser.usuarios

  fun processLogin() {
    val user = usuarios.find {it.login == username && it.senha == password}
    if(user == null) logout()
    else login(user)
  }

  fun login(user: LoginUser) {
    SecurityUtils.login(user)
  }

  fun logout() {
    SecurityUtils.logout()
  }
}