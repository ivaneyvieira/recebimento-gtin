package br.com.pintos.recebimento_gtin.view

import br.com.pintos.recebimento_gtin.viewmodel.LoginViewModel
import br.com.pintos.recebimento_gtin.viewmodel.SecurityUtils
import com.github.mvysny.karibudsl.v10.navigateToView
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.dependency.HtmlImport
import com.vaadin.flow.component.login.LoginI18n
import com.vaadin.flow.component.login.LoginOverlay
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.page.Viewport
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route

@Route("login")
@PageTitle("Recebimento GTIN")
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes")
class LoginView: VerticalLayout(), BeforeEnterObserver {
  private val login = LoginOverlay()
  val model = LoginViewModel()

  init {
    val i18n = LoginI18n.createDefault()
    i18n.header = LoginI18n.Header()
    i18n.header.title = "Recebimento GTIN"
    i18n.header.description = ""
    i18n.additionalInformation = null
    i18n.form = LoginI18n.Form()
    i18n.form.submit = "Login"
    i18n.form.title = "Login"
    i18n.form.username = "Email"
    i18n.form.password = "Senha"
    login.setI18n(i18n)
    login.isForgotPasswordButtonVisible = false
    //login.action = "login"
    login.isOpened = true
    login.addLoginListener {e ->
      model.username = e.username ?: ""
      model.password = e.password
      model.processLogin()
      if(model.loginOk) {
        login.close()
        navigateToMainPage()
      }
      else {
        login.isError = true
        model.logout()
      }
    }
  }

  private fun navigateToMainPage() {
    UI.getCurrent().navigate("")
  }

  override fun beforeEnter(event: BeforeEnterEvent) {
    if(SecurityUtils.isUserLoggedIn()) {
      UI.getCurrent()
        .page.history.replaceState(null, "")
      event.rerouteTo(AssociacaoGtinView::class.java)
    }
  }
}