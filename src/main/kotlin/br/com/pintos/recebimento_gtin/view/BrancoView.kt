package br.com.pintos.recebimento_gtin.view

import com.vaadin.flow.component.UI
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

@Route("")
class BrancoView : VerticalLayout(){
  init {
    UI.getCurrent()
      .navigate(AssociacaoGtinView::class.java)
  }
}