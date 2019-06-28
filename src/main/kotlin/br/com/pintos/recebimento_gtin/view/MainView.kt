package br.com.pintos.recebimento_gtin.view

import br.com.pintos.recebimento_gtin.viewmodel.SecurityUtils
import com.vaadin.flow.component.HasElement
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.applayout.AbstractAppRouterLayout
import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.applayout.AppLayoutMenu
import com.vaadin.flow.component.applayout.AppLayoutMenuItem
import com.vaadin.flow.component.html.H3
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.page.Viewport
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.theme.Theme
import com.vaadin.flow.theme.lumo.Lumo
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener

//@Theme(value = Material::class, variant = Material.LIGHT)
//@PWA(name = "Recebimento GTIN", shortName = "GTIN", display = "fullscreen")
@PageTitle("Recebimento GTIN")
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes")
@Theme(Lumo::class)
class MainView: AbstractAppRouterLayout() {
  override fun configure(appLayout: AppLayout, menu: AppLayoutMenu) {
    appLayout.setBranding(H3("Pintos LTDA"))
    setMenuItem(menu, AppLayoutMenuItem(VaadinIcon.EDIT.create(), "Recebimento", AssociacaoGtinView.ROUTE))
    if(SecurityUtils.user?.isAdmin == true) setMenuItem(menu,
                                                        AppLayoutMenuItem(VaadinIcon.COG.create(),
                                                                          "Configuração",
                                                                          ConfigView.ROUTE))
    setMenuItem(menu, AppLayoutMenuItem(VaadinIcon.OUT.create(), "Sair") {
      SecurityUtils.logout()
      UI.getCurrent()
        .navigate(LoginView.ROUTE)
    })
  }

  private fun setMenuItem(menu: AppLayoutMenu, menuItem: AppLayoutMenuItem) {
    menuItem.element.setAttribute("theme", "icon-on-top")
    menu.addMenuItem(menuItem)
  }

  override fun beforeNavigate(route: String?, content: HasElement?) {
    if(!SecurityUtils.isLogged) {
      UI.getCurrent()
        .navigate(LoginView.ROUTE)
    }
    else if(route == "") UI.getCurrent().navigate(AssociacaoGtinView.ROUTE)
    else super.beforeNavigate(route, content)
  }
}

@WebListener
class ServiceListener: ServletContextListener {
  fun configDB() {
    val home = System.getenv("HOME")
    val fileName = System.getenv("EBEAN_PROPS") ?: "$home/ebean.saci.properties"
    println("Salvando configurações $fileName")
    System.setProperty("ebean.props.file", fileName)
  }

  override fun contextInitialized(sce: ServletContextEvent?) {
   // log.info("Initializing Database")
    configDB()
  }

  override fun contextDestroyed(sce: ServletContextEvent?) {
    //log.info("Shutting down")
   // log.info("Destroying VaadinOnKotlin")
    //VaadinOnKotlin.destroy()
    //log.info("Shutdown complete")
  }

  companion object {
    //private val log = LoggerFactory.getLogger(Bootstrap::class.java)
  }
}