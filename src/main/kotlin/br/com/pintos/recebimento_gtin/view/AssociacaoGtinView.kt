package br.com.pintos.recebimento_gtin.view

import br.com.pintos.framework.utils.format
import br.com.pintos.framework.utils.localDate
import br.com.pintos.recebimento_gtin.model.Produto
import br.com.pintos.recebimento_gtin.viewmodel.AssociacaoGtinViewModel
import com.github.mvysny.karibudsl.v10.addColumnFor
import com.github.mvysny.karibudsl.v10.button
import com.github.mvysny.karibudsl.v10.grid
import com.github.mvysny.karibudsl.v10.horizontalLayout
import com.github.mvysny.karibudsl.v10.isExpand
import com.github.mvysny.karibudsl.v10.textField
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.component.textfield.TextFieldVariant
import com.vaadin.flow.data.binder.Binder
import com.vaadin.flow.router.Route

@Route("", layout = MainView::class)
class AssociacaoGtinView: VerticalLayout() {
  private var gridProdutos: Grid<Produto>
  private lateinit var dataNota: TextField
  private lateinit var fornecedor: TextField
  private lateinit var numeroNota: TextField
  private lateinit var numeroInterno: TextField
  val model = AssociacaoGtinViewModel()
  private lateinit var edtChave: TextField

  init {
    this.setHeightFull()
    horizontalLayout {
      this.setWidthFull()
      edtChave = textField("Chave da nota") {
        this.isExpand = true
        isAutoselect = true
        isAutofocus = true
      }
      button("Pesquisar") {
        addClickListener {e ->
          model.nfeKey = edtChave.value
          model.localizaNfeKey(::update)
        }
      }
      this.expand(edtChave)
    }
    horizontalLayout {
      this.setWidthFull()
      numeroInterno = textField("Número Interno") {
        isReadOnly = true
        addThemeVariants(TextFieldVariant.LUMO_SMALL)
      }
      numeroNota = textField("Número") {
        addThemeVariants(TextFieldVariant.LUMO_SMALL)
        isReadOnly = true
      }
      fornecedor = textField("Fornecedor") {
        isReadOnly = true
        addThemeVariants(TextFieldVariant.LUMO_SMALL)
      }
      dataNota = textField("Data") {
        isReadOnly = true
        addThemeVariants(TextFieldVariant.LUMO_SMALL)
      }
    }
    gridProdutos = grid<Produto> {
      this.setSizeFull()
      val field = TextField().apply {
        width = "100%"
      }
      field.element.addEventListener("keydown") {event ->
        val element = editor.item
        this.editor.closeEditor()
        //model.saveProduto(element)
        val proximo = model.proximoProduto(element)
        proximo?.let {
          this.editor.editItem(proximo)
          field.focus()
          this.select(proximo)
        }
      }
        .filter = "event.key === 'Enter'"
      addColumnFor(Produto::codigo) {
        this.setHeader("Código")
        this.flexGrow = 1
      }
      addColumnFor(Produto::descricao) {
        this.setHeader("Descrição")
        this.flexGrow = 5
      }
      addColumnFor(Produto::grade) {
        this.setHeader("Grade")
        this.flexGrow = 1
      }
      addColumnFor(Produto::quant) {
        this.setHeader("Quantidade")
        this.flexGrow = 1
      }
      addColumnFor(Produto::gtin) {
        this.setHeader("GTIN")
        this.flexGrow = 5
        this.editorComponent = field
      }
      val binder = Binder(Produto::class.java)
      this.editor.binder = binder
      this.editor.addSaveListener {e ->
        val produto = e.item
        model.saveProduto(produto)
      }

      binder.bind(field, Produto::gtin.name)
      //editor.isBuffered = true
      this.addItemClickListener {e ->
        this.editor.editItem(e.item)
        field.focus()
      }
      binder.addValueChangeListener {e ->
        val produto = e.value as? Produto
        model.saveProduto(produto)
      }
    }
  }

  fun update(erro: String?) {
    if(erro == null) {
      fornecedor.value = model.fornecedor
      numeroInterno.value = model.invno.toString()
      numeroNota.value = "${model.numero}/${model.serie}"
      dataNota.value = model.dataEmissao.localDate()
        .format()
      gridProdutos.setItems(model.produtos)
    }
    else {
      val notification = Notification(erro, 3000)
      notification.open()
    }
  }
}