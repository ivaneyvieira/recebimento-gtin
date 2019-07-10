package br.com.pintos.recebimento_gtin.model

import br.com.pintos.framework.model.DB
import br.com.pintos.framework.model.QueryDB
import br.com.pintos.framework.utils.lpad

class QuerySaci: QueryDB(driver, url, username, password) {
  fun findUsuarios(): List<LoginUser> {
    val sql = "/sql/loginUser.sql"
    return query(sql) {q ->
      q.executeAndFetch(LoginUser::class.java)
    }
  }

  fun findNotaEntrada(nfeKey: String): List<NotaEntrada> {
    val sql = "/sql/notaEntrada.sql"
    return query(sql) {q ->
      q.addParameter("nfekey", nfeKey)
        .executeAndFetch(NotaEntrada::class.java)
    }
  }

  fun findProdutoNota(invno: Int): List<Produto> {
    val sql = "/sql/produtosNota.sql"
    return query(sql) {q ->
      q.addParameter("invno", invno)
        .executeAndFetch(Produto::class.java)
    }
  }

  fun salvaProduto(codigo: String, grade: String, gtin: String) {
    val sql = "/sql/salvaProduto.sql"
    query(sql) {q ->
      q.addParameter("prdno", codigo.lpad(16, " "))
        .addParameter("grade", grade)
        .addParameter("gtin", gtin.lpad(16, " "))
        .executeUpdate()
    }
  }

  fun gtinJaCadastrado(codigo: String, grade: String, gtin: String): Boolean {
    val sql = "/sql/gtinJaCadastrado.sql"
    println("gtinJaCadastrado('$codigo', '$grade', '$gtin'")
    return query(sql) {q ->
      q.addParameter("prdno", codigo.lpad(16, " "))
        .addParameter("grade", grade)
        .addParameter("gtin", gtin.lpad(16, " "))
        .executeScalar(Int::class.java) > 0
    }
  }

  companion object {
    private val db = DB("saci")
    internal val driver = db.driver
    internal val url = db.url
    internal val username = db.username
    internal val password = db.password
    val ipServer = url.split("/")
      .getOrNull(2)
  }
}

val saci = QuerySaci()