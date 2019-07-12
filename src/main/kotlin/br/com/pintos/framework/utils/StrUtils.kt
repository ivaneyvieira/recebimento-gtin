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
  val digit: Int
  val calculated: Int
  val ean: String
  val checkSum = "131313131313"
  var sum = 0

  return if(this.length == 8 || this.length == 13) {
    digit = Integer.parseInt("" + this[this.length - 1])
    ean = this.substring(0, this.length - 1)
    for(i in 0 until ean.length) {
      sum += Integer.parseInt("" + ean[i]) * Integer.parseInt("" + checkSum[i])
    }
    calculated = (10 - sum % 10) % 10
    digit == calculated
  }
  else {
    false
  }
}

