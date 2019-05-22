package br.com.pintos.recebimento_gtin.view

import com.vaadin.flow.component.dependency.HtmlImport
import com.vaadin.flow.component.login.LoginI18n
import com.vaadin.flow.component.login.LoginOverlay
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.page.Viewport
import com.vaadin.flow.router.AfterNavigationEvent
import com.vaadin.flow.router.AfterNavigationObserver
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route

@Route("login")
@PageTitle("Vaadin Demo Bakery App")
@HtmlImport("styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes")
class LoginView: VerticalLayout(), AfterNavigationObserver, BeforeEnterObserver {
  private val login = LoginOverlay()

  init {
    val i18n = LoginI18n.createDefault()
    i18n.header = LoginI18n.Header()
    i18n.header.title = "Vaadin Demo Bakery App"
    i18n.header.description = "admin@vaadin.com + admin\n" + "barista@vaadin.com + barista"
    i18n.additionalInformation = null
    i18n.form = LoginI18n.Form()
    i18n.form.submit = "Sign in"
    i18n.form.title = "Sign in"
    i18n.form.username = "Email"
    i18n.form.password = "Password"
    login.setI18n(i18n)
    login.isForgotPasswordButtonVisible = false
    login.action = "login"
    login.isOpened = true
  }

  override fun beforeEnter(event: BeforeEnterEvent) {
    //   if (SecurityUtils.isUserLoggedIn()) {
    // Needed manually to change the URL because of https://github.com/vaadin/flow/issues/4189
    //       UI.getCurrent().getPage().getHistory().replaceState(null, "");
    //        event.rerouteTo(StorefrontView.class);
    //   }
  }

  override fun afterNavigation(event: AfterNavigationEvent) {
    login.isError = event.location.queryParameters.parameters.containsKey("error")
  }
}