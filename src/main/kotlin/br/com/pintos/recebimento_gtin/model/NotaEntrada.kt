package br.com.pintos.recebimento_gtin.model

class NotaEntrada(val invno: Int,
                  val storeno: Int,
                  val numero: String,
                  val serie: String,
                  val vendno: Int,
                  val fornecedor: String,
                  val dataEmissao: Int) {
  companion object {
    fun findNotaEntrada(nfeKey: String): NotaEntrada? {
      return saci.findNotaEntrada(nfeKey)
        .firstOrNull()
    }
  }

  val produtos: List<Produto>
    get() = saci.findProdutoNota(invno)
}