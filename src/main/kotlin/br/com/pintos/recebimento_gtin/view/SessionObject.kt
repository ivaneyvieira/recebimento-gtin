package br.com.pintos.recebimento_gtin.view

import com.vaadin.flow.component.UI

inline fun <reified T: Any> UI.create(createClass: () -> T): T {
  val value = this.session.getAttribute(T::class.java)
  return if(value == null) {
    val valueCreate = createClass()
    this.session.setAttribute(T::class.java, valueCreate)
    valueCreate
  }
  else value
}