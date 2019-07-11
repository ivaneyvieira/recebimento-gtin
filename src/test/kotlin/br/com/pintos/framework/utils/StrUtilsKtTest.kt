package br.com.pintos.framework.utils

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.junit.Arquillian
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.asset.EmptyAsset
import org.jboss.shrinkwrap.api.spec.JavaArchive
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(Arquillian::class)
class StrUtilsKtTest {
  @org.junit.Test
  fun isValidBarCodeEAN() {
  }

  companion object {
    @Deployment
    fun createDeployment(): JavaArchive {
      return ShrinkWrap.create(JavaArchive::class.java)
        .addClass(StrUtilsKt::class.java)
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
    }
  }
}
