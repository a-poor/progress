package progress

/**
 *  @constructor Create a new Progress bar
 *  @param n Number of elements in the list
 *  @param width Size of the
 *
 */
class Progress(n: Int, width: Int = 80) {
  var pos = 0

  /** Clear the bar's progress by
   *  resetting `pos` to 0.
   *
   */
  def reset(): Unit = {
    pos = 0
  }

  private def formatBar(): String = {
    // Constants
    val filled = "█"
    val unfilled = " "
    val nPadChars = 9 // Brackets, a space, 5-chars, and a percent sign

    // Calculate bar length
    val pctComplete = pos / n.toDouble
    val barWidth = width - nPadChars
    val nFilled = (barWidth * pctComplete).toInt
    val nUnfilled = barWidth - nFilled

    f"[${ filled * nFilled }${ unfilled * nUnfilled }] ${ pctComplete * 100 }%5.1f%%"
  }

  /** Increment the bar's position.
   *
   * Limits `pos` to the range: `0 <= pos <= n`
   *
   * @param by Amount to increment `pos`
   */
  def inc(by: Int = 1) = {
    pos = (pos + by).min(n).max(0)
  }

  /** Clear the bar from the terminal.
   *
   */
  def clear(): Unit = {
    print("\r\u001b[K")
  }

  /** Write the bar to the terminal
   *
   * @param newLine Should a new line be added after?
   */
  def write(newLine: Boolean = false) = {
    clear()
    print(formatBar())
    if (newLine) println()
  }

  /** Safely write a line to the terminal above the bar.
   *
   * Works by clearing the bar. Calling `println(x)`.
   * And then re-writing the bar to the terminal.
   *
   * @param x Value to print to the terminal
   */
  def bprintln(x: Any): Unit = {
    clear()
    println(x)
    write()
  }

}

object Progress {
  /** Factory for [[progress.Progress]] instances.
   *
   * @param n Number of positions in the bar?
   * @param width Display width of the bar.
   * @return
   */
  def apply(n: Int = 100, width: Int = 80) = {
    new Progress(n, width)
  }

}

