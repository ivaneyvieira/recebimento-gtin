package rest

import br.com.pintos.recebimento_gtin.model.NotaEntrada
import br.com.pintos.recebimento_gtin.model.Produto
import br.com.pintos.recebimento_gtin.viewmodel.AssociacaoGtinViewModel
import br.com.pintos.recebimento_gtin.viewmodel.IView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

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

  @PostMapping("/saveProduto")
  fun saveProduto(@RequestBody produto : Produto)    : Messagem {
    messagem.clean()
    viewModel.saveProduto(produto)
    return messagem
  }
}

data class Messagem(var erro: String = "", var aviso: String = "", val data : LocalDateTime = LocalDateTime.now()) {
  fun clean() {
    erro = ""
    aviso = ""
  }
}

