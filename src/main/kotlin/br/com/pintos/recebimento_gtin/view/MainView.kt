package br.com.pintos.recebimento_gtin.view

import br.com.pintos.recebimento_gtin.viewmodel.SecurityUtils
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.applayout.AbstractAppRouterLayout
import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.applayout.AppLayoutMenu
import com.vaadin.flow.component.applayout.AppLayoutMenuItem
import com.vaadin.flow.component.html.Span
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.page.Viewport

@Viewport("Recebimento")
class MainView: AbstractAppRouterLayout() {
  init {
    SecurityUtils.configDB()
    if(!SecurityUtils.isUserLoggedIn())
      UI.getCurrent().navigate("login")
  }



  override fun configure(appLayout: AppLayout, menu: AppLayoutMenu) {
    appLayout.setBranding(Span("Pintos"))
    setMenuItem(menu, AppLayoutMenuItem(VaadinIcon.EDIT.create(), "Recebimento", ""))
    setMenuItem(menu, AppLayoutMenuItem(VaadinIcon.COG.create(), "Configuração", ""))
    setMenuItem(menu, AppLayoutMenuItem(VaadinIcon.OUT.create(), "Sair") {
      SecurityUtils.logout()
      UI.getCurrent().navigate("login")
    })
  }

  private fun setMenuItem(menu: AppLayoutMenu, menuItem: AppLayoutMenuItem) {
    menuItem.element.setAttribute("theme", "icon-on-top")
    menu.addMenuItem(menuItem)
  }
}