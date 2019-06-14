package br.com.pintos.recebimento_gtin.view

import br.com.pintos.framework.utils.format
import br.com.pintos.framework.utils.localDate
import br.com.pintos.recebimento_gtin.model.Produto
import br.com.pintos.recebimento_gtin.view.AssociacaoGtinView.Companion.ROUTE
import br.com.pintos.recebimento_gtin.viewmodel.AssociacaoGtinViewModel
import br.com.pintos.recebimento_gtin.viewmodel.IView
import com.github.mvysny.karibudsl.v10.KeyShortcut
import com.github.mvysny.karibudsl.v10.VaadinDsl
import com.github.mvysny.karibudsl.v10.addColumnFor
import com.github.mvysny.karibudsl.v10.addShortcut
import com.github.mvysny.karibudsl.v10.getAll
import com.github.mvysny.karibudsl.v10.grid
import com.github.mvysny.karibudsl.v10.horizontalLayout
import com.github.mvysny.karibudsl.v10.isExpand
import com.github.mvysny.karibudsl.v10.textField
import com.vaadin.flow.component.Key
import com.vaadin.flow.component.Key.ENTER
import com.vaadin.flow.component.Key.F2
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.grid.GridVariant.LUMO_COLUMN_BORDERS
import com.vaadin.flow.component.grid.GridVariant.LUMO_COMPACT
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.notification.Notification.Position.MIDDLE
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.component.textfield.TextFieldVariant.LUMO_SMALL
import com.vaadin.flow.data.binder.Binder
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.router.Route

@Route(ROUTE, layout = MainView::class)
class AssociacaoGtinView: IView, VerticalLayout() {
  companion object {
    const val ROUTE = "recebimento"
  }

  val model: AssociacaoGtinViewModel = UI.getCurrent()
    .create {AssociacaoGtinViewModel(this)}
  private var dadosNota: HorizontalLayout
  private var gridProdutos: Grid<Produto>
  private lateinit var dataNota: TextField
  private lateinit var fornecedor: TextField
  private lateinit var numeroNota: TextField
  private lateinit var numeroInterno: TextField
  private lateinit var edtChave: TextField

  init {
    this.setHeightFull()

    this.setWidthFull()
    horizontalLayout {
      isMargin = false
      this.setWidthFull()
      edtChave = textField("Chave da nota (F2)") {
        this.isExpand = true
        isAutoselect = true
        isAutofocus = true
        this.addShortcut(KeyShortcut(ENTER)) {
          model.nfeKey = edtChave.value
          model.localizaNfeKey(::update)
        }
        valueChangeMode = ValueChangeMode.EAGER
        this.addFocusShortcut(F2)
      }
      this.expand(edtChave)
    }
    dadosNota = horizontalLayout {
      this.setWidthFull()
      isVisible = false
      numeroInterno = textField("Número Interno") {
        isReadOnly = true
        addThemeVariants(LUMO_SMALL)
      }
      numeroNota = textField("Número") {
        addThemeVariants(LUMO_SMALL)
        isReadOnly = true
      }
      fornecedor = textField("Fornecedor") {
        isReadOnly = true
        addThemeVariants(LUMO_SMALL)
      }
      dataNota = textField("Data") {
        isReadOnly = true
        addThemeVariants(LUMO_SMALL)
      }
    }
    gridProdutos = grid {
      this.setSizeFull()
      isMultiSort = false
      addThemeVariants(LUMO_COMPACT, LUMO_COLUMN_BORDERS)
      val field = TextField().apply {
        width = "100%"
        isAutoselect = true
        isAutofocus = true
      }
      this.addShortcut(KeyShortcut(Key.ENTER)) {
        val item = this.selectedItems.firstOrNull() ?: this.dataProvider.getAll().firstOrNull()
        if(item != null) {
          val row =  this.dataProvider.getAll().indexOf(item)
         // UI.getCurrent().getPage().executeJavaScript("$0._scrollToIndex($1)", this, row)
          this.select(item)
          this.editor.editItem(item)
          field.fieldFocus()
        }
      }
      field.addKeyDownListener {eKey ->
        when {
          eKey.key.matches(Key.ENTER.keys[0])      -> {
            val element = editor.item
            val proximo = model.proximoProduto(element)

            changeEditor(proximo, field)
          }
          eKey.key.matches(Key.ARROW_DOWN.keys[0]) -> {
            val element = editor.item
            val proximo = model.proximoProduto(element)

            changeEditor(proximo, field)
          }
          eKey.key.matches(Key.ARROW_UP.keys[0])   -> {
            val element = editor.item
            val proximo = model.anteriorProduto(element)

            changeEditor(proximo, field)
          }
        }
      }
      addColumnFor(Produto::codigo) {
        this.setHeader("Código")
        this.flexGrow = 1
        isSortable = false
      }
      addColumnFor(Produto::descricao) {
        this.setHeader("Descrição")
        this.flexGrow = 5
        isSortable = false
      }
      addColumnFor(Produto::grade) {
        this.setHeader("Grade")
        this.flexGrow = 1
        isSortable = false
      }
      addColumnFor(Produto::quant) {
        this.setHeader("Quantidade")
        this.flexGrow = 1
        isSortable = false
      }
      addColumnFor(Produto::gtin) {
        this.setHeader("GTIN")
        this.flexGrow = 5
        this.editorComponent = field
        isSortable = false
      }
      val binder = Binder(Produto::class.java)
      this.editor.binder = binder
      this.addSelectionListener {eSel ->
        val produto = eSel.allSelectedItems.firstOrNull()
        produto?.let {
          this.editor.editItem(produto)
          field.fieldFocus()
        }
      }
      binder.bind(field, Produto::gtin.name)
      this.addItemClickListener {e ->
        this.editor.editItem(e.item)
        field.fieldFocus()
      }

      binder.addValueChangeListener {e ->
        val produto = binder.bean
        model.saveProduto(produto) {sucesso ->
          if(!sucesso) {
            produto.gtin = ""
            this.editor.editItem(produto)
            field.fieldFocus()
          }
        }
      }
    }
  }

  private fun @VaadinDsl Grid<Produto>.changeEditor(produto: Produto?, field: TextField) {
    if(produto != null) {
      if(this.editor.isOpen) this.editor.closeEditor()
      this.editor.editItem(produto)
      this.select(produto)
      field.fieldFocus()
    }
  }

  private fun TextField.fieldFocus() {
    Thread.sleep(100)
    this.focus()
  }

  fun update(sucesso: Boolean) {
    if(sucesso) {
      dadosNota.isVisible = true
      fornecedor.value = model.fornecedor
      numeroInterno.value = model.invno.toString()
      numeroNota.value = "${model.numero}/${model.serie}"
      dataNota.value = model.dataEmissao.localDate()
        .format()
      gridProdutos.setItems(model.produtos)

      gridProdutos.focus()
      gridProdutos.select(model.produtos.firstOrNull())
    }
    else {
      dadosNota.isVisible = false
      gridProdutos.setItems(emptyList())
      edtChave.fieldFocus()
    }
  }

  override fun showErro(msg: String) {
    Notification.show(msg, 1000, MIDDLE)
  }

  override fun showAviso(msg: String) {
    Notification.show(msg, 1000, MIDDLE)
  }
}