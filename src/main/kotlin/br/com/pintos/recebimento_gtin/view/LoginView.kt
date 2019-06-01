package br.com.pintos.recebimento_gtin.view

import br.com.pintos.recebimento_gtin.viewmodel.LoginViewModel
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.login.LoginI18n
import com.vaadin.flow.component.login.LoginOverlay
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.Route

@Route("login")
class LoginView: VerticalLayout(), BeforeEnterObserver {
  val model = LoginViewModel()
  private val login = LoginOverlay()

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
    i18n.form.forgotPassword = "Esqueci minha senha"
    i18n.errorMessage.title = "Usuário/senha inválidos"
    i18n.errorMessage.message = "Confira seu usuário e senha e tente novamente."
    //i18n.additionalInformation = "Caso necessite apresentar alguma informação extra para o usuário (como
    // credenciais padrão), este é o lugar."
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
      }
    }
  }

  private fun navigateToMainPage() {
    UI.getCurrent()
      .navigate(AssociacaoGtinView::class.java)
  }

  override fun beforeEnter(event: BeforeEnterEvent) {
    if(model.loginOk) {
      UI.getCurrent()
        .page.history.replaceState(null, "")
      event.rerouteTo(AssociacaoGtinView::class.java)
    }
  }
}