package br.com.pintos.recebimento_gtin.model

import br.com.pintos.framework.model.QueryDB
import br.com.pintos.framework.model.DB

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