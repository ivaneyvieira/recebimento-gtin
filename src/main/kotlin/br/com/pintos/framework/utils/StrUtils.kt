package br.com.pintos.framework.utils

fun String?.lpad(size: Int, filler: String): String {
  var str = this ?: ""
  if(str.length > size) return str.substring(0, size)
  val buf = StringBuilder(str)
  while(buf.length < size) buf.insert(0, filler)

  str = buf.toString()
  return str
}

fun String?.rpad(size: Int, filler: String): String {
  val str = this ?: ""
  if(str.length > size) return str.substring(0, size)
  val buf = StringBuilder(str)
  while(buf.length < size) buf.append(filler)

  return buf.toString()
}

fun String?.trimNull(): String {
  return this?.trim {it <= ' '} ?: ""
}

fun String.mid(start: Int, len: Int): String {
  return if(this == "") ""
  else {
    val end = start + len
    val pStart = when {
      start < 0       -> 0
      start >= length -> length - 1
      else            -> start
    }
    val pEnd = when {
      end < 0      -> 0
      end > length -> length
      else         -> end
    }
    if(pStart <= pEnd) substring(pStart, pEnd)
    else ""
  }
}

fun String.mid(start: Int): String {
  return mid(start, start + length)
}

fun String.isValidBarCodeEAN(): Boolean {
  val checkSum = "131313131313".map {it.toString().toInt()}

  return if((this.length == 8 || this.length == 13) && this.matches("[0-9]+".toRegex())) {
    val digit = this[this.length - 1].toString().toInt()
    val ean = this.substring(0, this.length - 1)
      .map {it.toString().toInt()}
    val total = ean.zip(checkSum)
      .fold(0) {sum, (a, b) ->
        sum + a * b
      }
    val calculated = 10 - total % 10
    digit == calculated
  }
  else false
}