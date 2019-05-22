package br.com.pintos.framework.utils

/*
fun ByteArray.makeResource(): Resource {
  val nome = md5()
  val imagesource = BytesStreamSource(this)
  val streamResource = StreamResource(imagesource, "$nome.jpg")
  streamResource.cacheTime = 1000
  return streamResource
}

fun ByteArray.md5(): String {
  val md5Digest = MessageDigest.getInstance("MD5")
  val bytes = md5Digest.digest(this)
  return DatatypeConverter.printHexBinary(bytes)
}

class BytesStreamSource(private val imagem: ByteArray): StreamSource {
  override fun getStream(): InputStream {
    return ByteArrayInputStream(imagem)
  }
}
*/
