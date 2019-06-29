package rest

import br.com.pintos.recebimento_gtin.model.NotaEntrada
import br.com.pintos.recebimento_gtin.viewmodel.AssociacaoGtinViewModel
import br.com.pintos.recebimento_gtin.viewmodel.IView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GtinControler: IView {
  val messagem = Messagem()
  override fun showErro(msg: String) {
    messagem.clean()
    messagem.erro = msg
  }

  override fun showAviso(msg: String) {
    messagem.clean()
    messagem.aviso = msg
  }

  val viewModel = AssociacaoGtinViewModel(this)

  @GetMapping("/messagem")
  fun messagem(): Messagem {
    return messagem
  }

  @GetMapping("/gtin/{key}")
  fun findNotaEntrada(@PathVariable("key")
                      key: String): NotaEntrada? {
    return viewModel.findNotaEntrada(key)
  }

  @GetMapping("/save/{key}/{prdno}/{grade}/{gtin}")
  fun saveProduto(@PathVariable
                  key: String,
                  @PathVariable
                  prdno: String,
                  @PathVariable
                  grade: String,
                  @PathVariable
                  gtin: String)
    : Messagem {
    viewModel.saveProduto(key, prdno, grade, gtin)
    return messagem
  }
}

data class Messagem(var erro: String = "", var aviso: String = "") {
  fun clean() {
    erro = ""
    aviso = ""
  }
}