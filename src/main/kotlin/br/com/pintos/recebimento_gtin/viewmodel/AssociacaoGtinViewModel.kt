package br.com.pintos.recebimento_gtin.viewmodel

import br.com.pintos.recebimento_gtin.model.NotaEntrada
import br.com.pintos.recebimento_gtin.model.Produto

class AssociacaoGtinViewModel(val view: IView) {
  var produtos: List<Produto> = emptyList()
  var invno: Int = 0
  var storeno: Int = 0
  var numero: String = ""
  var serie: String = ""
  var vendno: Int = 0
  var fornecedor: String = ""
  var dataEmissao: Int = 0
  var nfeKey: String = ""

  private fun showErro(msg: String) {
    view.showErro(msg)
  }

  private fun showAviso(msg: String) {
    view.showAviso(msg)
  }

  fun localizaNfeKey(update: (Boolean) -> Unit) {
    val notaEntrada = NotaEntrada.findNotaEntrada(nfeKey)
    if(notaEntrada == null) {
      showErro("Nota não encontrada")
      update(false)
    }
    else {
      invno = notaEntrada.invno
      storeno = notaEntrada.storeno
      numero = notaEntrada.numero
      serie = notaEntrada.serie
      vendno = notaEntrada.vendno
      fornecedor = notaEntrada.fornecedor
      dataEmissao = notaEntrada.dataEmissao
      produtos = notaEntrada.produtos
      update(true)
    }
  }

  fun List<Produto>.find(produto: Produto): Int {
    val prd = this.find {it.codigo == produto.codigo && it.grade == produto.grade} ?: return -1
    return this.indexOf(prd)
  }

  fun proximoProduto(produto: Produto?): Produto? {
    produto ?: return null
    val pos = produtos.find(produto)
    return if(pos >= 0) {
      if(pos >= (produtos.size - 1)) produtos.getOrNull(0)
      else produtos.getOrNull(pos + 1)
    }
    else null
  }

  fun anteriorProduto(produto: Produto?): Produto? {
    produto ?: return null
    val pos = produtos.find(produto)
    return if(pos >= 0) {
      if(pos == 0) produtos.getOrNull(produtos.size - 1)
      else produtos.getOrNull(pos - 1)
    }
    else null
  }

  fun saveProduto(produto: Produto?, update: (Boolean) -> Unit) {
    if(produto == null) {
      showErro("Produto inválido")
      update(false)
    }
    else if(produto.gtin == "") {
      produto.save()
      update(true)
    }
    else if(!produto.temGrade()) {
      showErro("Produto não tem grade")
      update(false)
    }
    else if(!produto.digitoValido()) {
      showErro("Código GTIN inválido")
      update(false)
    }
    else if(produto.gtinJaCadastrado()) {
      showErro("Código GTIN Já está cadastrado em outro produto")
      update(false)
    }
    else {
      produto.save()
      update(true)
    }
  }
}

interface IView {
  fun showErro(msg: String)
  fun showAviso(msg: String)
}