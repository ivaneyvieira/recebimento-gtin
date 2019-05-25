package br.com.pintos.recebimento_gtin.viewmodel

import br.com.pintos.recebimento_gtin.model.NotaEntrada
import br.com.pintos.recebimento_gtin.model.Produto

class AssociacaoGtinViewModel {
  var produtos: List<Produto> = emptyList()
  var invno: Int = 0
  var storeno: Int = 0
  var numero: String = ""
  var serie: String = ""
  var vendno: Int = 0
  var fornecedor: String = ""
  var dataEmissao: Int = 0
  var nfeKey: String = ""
  fun localizaNfeKey(update: (String?) -> Unit) {
    val notaEntrada = NotaEntrada.findNotaEntrada(nfeKey)
    if(notaEntrada == null) update("Nota não encontrada")
    else {
      invno = notaEntrada.invno
      storeno = notaEntrada.storeno
      numero = notaEntrada.numero
      serie = notaEntrada.serie
      vendno = notaEntrada.vendno
      fornecedor = notaEntrada.fornecedor
      dataEmissao = notaEntrada.dataEmissao
      produtos = notaEntrada.produtos
      update(null)
    }
  }

  fun proximoProduto(element: Produto?): Produto? {
    element ?: return null
    val pos = produtos.indexOf(element)
    return if(pos >= 0) {
      if(pos >= (produtos.size - 1)) produtos.getOrNull(0)
      else produtos.getOrNull(pos+1)
    }
    else null
  }

  fun saveProduto(produto: Produto?) {
    produto?.save()
  }
}