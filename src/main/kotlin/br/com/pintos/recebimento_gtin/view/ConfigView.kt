package br.com.pintos.recebimento_gtin.view

import br.com.pintos.recebimento_gtin.view.ConfigView.Companion.ROUTE
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

@Route(ROUTE, layout = MainView::class)
class ConfigView: VerticalLayout() {
  companion object {
    const val ROUTE = "config"
  }
}