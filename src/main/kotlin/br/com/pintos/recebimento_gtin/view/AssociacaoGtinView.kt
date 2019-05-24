package br.com.pintos.recebimento_gtin.view

import br.com.pintos.recebimento_gtin.model.Produtos
import br.com.pintos.recebimento_gtin.viewmodel.AssociacaoGtinViewModel
import com.github.mvysny.karibudsl.v10.button
import com.github.mvysny.karibudsl.v10.grid
import com.github.mvysny.karibudsl.v10.horizontalLayout
import com.github.mvysny.karibudsl.v10.isExpand
import com.github.mvysny.karibudsl.v10.label
import com.github.mvysny.karibudsl.v10.textField
import com.oracle.util.Checksums.update
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route

@Route("", layout = MainView::class)
class AssociacaoGtinView: VerticalLayout() {
  private lateinit var fornecedor: TextField
  private lateinit var numeroNota: TextField
  private lateinit var numeroInterno: TextField
  val model = AssociacaoGtinViewModel()
  private lateinit var edtChave: TextField

  init {
    this.setHeightFull()
    horizontalLayout {
      this.setWidthFull()
      edtChave = textField("Chave da nota") {
        this.isExpand = true
      }
      button("Pesquisar") {
        addClickListener {e ->
          model.nfeKey = edtChave.value
          model.localizaNfeKey(::update)
        }
      }
      this.expand(edtChave)
    }
    horizontalLayout {
      this.setWidthFull()
      numeroInterno = textField("Número Interno")
      numeroNota = textField("Número")
      fornecedor = textField("Fornecedor")
    }
    grid<Produtos> {
      this.setSizeFull()
    }
  }

  fun update(erro : String?) {
    if(erro == null) {
      fornecedor.value = model.fornecedor
    }else{
      val notification = Notification(erro, 3000)
      notification.open()
    }
  }
}