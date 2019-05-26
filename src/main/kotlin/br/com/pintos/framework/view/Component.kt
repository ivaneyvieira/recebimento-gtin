package br.com.pintos.framework.view

import com.github.appreciated.card.Card
import com.github.appreciated.card.ClickableCard
import com.github.appreciated.card.RippleClickableCard
import com.github.appreciated.card.StatefulCard
import com.github.mvysny.karibudsl.v10.VaadinDsl
import com.github.mvysny.karibudsl.v10.init
import com.vaadin.flow.component.HasComponents

@VaadinDsl
fun (@VaadinDsl HasComponents).card(block: (@VaadinDsl Card).() -> Unit = {}) = init(Card(), block)

@VaadinDsl
fun (@VaadinDsl HasComponents).clickableCard(block: (@VaadinDsl ClickableCard).() -> Unit = {}) =
  init(ClickableCard(), block)

@VaadinDsl
fun (@VaadinDsl HasComponents).rippleClickableCard(block: (@VaadinDsl RippleClickableCard).() -> Unit = {}) =
  init(RippleClickableCard(), block)

@VaadinDsl
fun (@VaadinDsl HasComponents).statefulCard(block: (@VaadinDsl StatefulCard).() -> Unit = {}) =
  init(StatefulCard(), block)

