package br.com.pintos.recebimento_gtin.model

import br.com.pintos.framework.utils.isValidBarCodeEAN

class Produto(val codigo: String,
              val descricao: String,
              val quant: Int,
              val grade: String,
              var gtin: String,
              val temGrade: Int) {
  override fun toString(): String {
    return "$codigo $grade"
  }

  fun save() {
    saci.salvaProduto(codigo, grade, gtin)
  }

  fun digitoValido(): Boolean {
    return gtin.isValidBarCodeEAN() || gtin == ""
  }

  fun temGrade(): Boolean {
    return temGrade != 0
  }

  fun gtinJaCadastrado(): Boolean {
    return saci.gtinJaCadastrado(codigo, grade, gtin)
  }
}
