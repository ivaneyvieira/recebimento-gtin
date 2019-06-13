package br.com.pintos.framework.model

import br.com.pintos.framework.utils.SystemUtils
import com.jolbox.bonecp.BoneCPDataSource
import org.sql2o.Query
import org.sql2o.Sql2o

open class QueryDB(private val driver: String, val url: String, val username: String, val password: String) {
  private val sql2o: Sql2o

  init {
    registerDriver(driver)
    val ds = BoneCPDataSource()
    ds.jdbcUrl = url
    ds.username = username
    ds.password = password
    ds.minConnectionsPerPartition = 4
    ds.maxConnectionsPerPartition = 2
    ds.partitionCount = 1
    this.sql2o = Sql2o(ds)
    //this.sql2o = Sql2o(url, username, password)
    //this.sql2o = Sql2o(dataSourceConfig())
  }

  private fun registerDriver(driver: String) {
    try {
      Class.forName(driver)
    } catch(e: ClassNotFoundException) {
      throw RuntimeException(e)
    }
  }

  protected fun <T> query(file: String, lambda: (Query) -> T): T {
    return buildQuery(file) {query ->
      val queryUpdate = query.dropLast(1)
      queryUpdate.forEach {update ->
        lambda(update)
      }
      val querySelect = query.last()
      val ret = lambda(querySelect)
      ret
    }
  }

  private inline fun <C: AutoCloseable, R> C.trywr(block: (C) -> R): R {
    this.use {
      return block(this)
    }
  }

  private fun <T> buildQuery(file: String, proc: (List<Query>) -> T): T {
    val sqls = SystemUtils.readFile(file)
      ?.split(";")
      .orEmpty()
      .map {it.trim()}
      .filter {it.isNotBlank()}
    this.sql2o.beginTransaction()
      .trywr {con ->
        val query = sqls.map {sql -> con.createQuery(sql)}
        val ret = proc(query)
        con.commit()
        return ret
      }
  }
}
