package br.com.pintos.recebimento_gtin.model

class Produto(val codigo : String, val descricao : String, val quant : Int,
val grade: String, var gtin: String){
  override fun toString(): String {
    return "$codigo $grade"
  }

  fun save() {
saci.salvaProduto(codigo, grade, gtin)
  }
}
