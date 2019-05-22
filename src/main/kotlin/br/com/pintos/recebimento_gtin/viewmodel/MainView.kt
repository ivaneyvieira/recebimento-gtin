package br.com.pintos.recebimento_gtin.viewmodel

import com.vaadin.flow.component.applayout.AbstractAppRouterLayout
import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.applayout.AppLayoutMenu
import com.vaadin.flow.component.applayout.AppLayoutMenuItem
import com.vaadin.flow.component.html.Span
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.page.Viewport

@Viewport("Recebimento")
class MainView: AbstractAppRouterLayout() {
  override fun configure(appLayout: AppLayout, menu: AppLayoutMenu) {
    appLayout.setBranding(Span("Pintos"))
    setMenuItem(menu, AppLayoutMenuItem(VaadinIcon.EDIT.create(), "Recebimento", ""))
  }

  private fun setMenuItem(menu: AppLayoutMenu, menuItem: AppLayoutMenuItem) {
    menuItem.element.setAttribute("theme", "icon-on-top")
    menu.addMenuItem(menuItem)
  }
}