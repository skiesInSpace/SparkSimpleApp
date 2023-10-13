package sample.other

import scala.util.Try

object ExceptionPatterMatching {
  def doSomeOperation(): String = {
    "a"
  }

  def main(args: Array[String]): Unit = {
    val triedString = Try(
      doSomeOperation())
    val yieldedString = for {
      value <- triedString
    } yield value

    println(yieldedString)
  }
}
