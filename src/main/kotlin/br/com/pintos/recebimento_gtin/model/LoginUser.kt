package br.com.pintos.recebimento_gtin.model

class LoginUser(val storeno: Int, val numero: Int, val login: String, val nome: String, val senha: String) {
  val isAdmin
    get() = login == "ADM"

  companion object {
    val usuarios get() = saci.findUsuarios()
  }
}