package br.com.pintos.recebimento_gtin.model

data class Produto constructor(var codigo: String = "",
                               var descricao: String = "",
                               var quant: Int = 0,
                               var grade: String = "",
                               var gtin: String = "",
                               var temGrade: Int = 0) {
  override fun toString(): String {
    return "$codigo $grade"
  }

  companion object {
    fun save(codigo: String, grade: String, gtin: String) {
      saci.salvaProduto(codigo, grade, gtin)
    }
  }

  fun save() {
    save(codigo, grade, gtin)
  }

  fun digitoValido(): Boolean {
    return gtin.length == 13 || gtin == ""
  }

  fun temGrade(): Boolean {
    return temGrade != 0
  }

  fun gtinJaCadastrado(): Boolean {
    return saci.gtinJaCadastrado(codigo, grade, gtin)
  }
}
