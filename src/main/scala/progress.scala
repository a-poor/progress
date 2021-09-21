//package progress

/**
 *  @constructor
 *  @param n Number of elements in the list
 *  @param width Size of the
 *
 */
class Progress(n: Int, width: Int = 80) {
  var pos = 0

  def reset(): Unit = {
    pos = 0
  }

  private def formatBar(): String = {
    // Constants
    val filled = "█"
    val unfilled = " "
    val nPadChars = 9 // Brackets, a space, 5-chars, and a percent sign

    val pctComplete = pos / n.toDouble
    val barWidth = width - nPadChars
    val nFilled = (barWidth * pctComplete).toInt
    val nUnfilled = barWidth - nFilled

    f"[${ filled * nFilled }${ unfilled * nUnfilled }] ${ pctComplete * 100 }%5.1f%%"
  }

  def inc(by: Int = 1) = {
    pos += by
  }

  def clear(): Unit = {
//    print("\r")
//    print("\r\033[K")
    print("\r\u001b[K")
  }

  def write(clearFirst: Boolean = true, newLine: Boolean = false) = {
    if (clearFirst) clear()
    print(formatBar())
    if (newLine) println()
  }

}

object Progress {
  def apply(n: Int, width: Int = 80) = {
    new Progress(n, width)
  }
}

@main def run() = {

  val x = 1.2345
  println(f"1 + $x = ${1 + x}%.2f")

  val b = Progress(200)
  b.write()
  
  for (_ <- 1 to 200) {
    b.inc(1)
    b.write()
    Thread.sleep((util.Random.nextDouble * 20).toInt)
  }
  
  b.write(newLine=true)

  println("Done.")
}
