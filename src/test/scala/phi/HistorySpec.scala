package phi

import org.scalatest._
import flatspec._
import matchers._

class HistorySpec extends AnyFlatSpec with should.Matchers {
  "History" should "calculate mean, variance, and standard deviation" in {
    val history = 17 +: 15 +: 23 +: 7 +: 9 +: 13 +: History.empty
    println(s"History: $history")
    println(s"Intervals: ${history.intervals}")
    val mean = history.mean
    println(s"History mean: $mean")
    assertResult(0.8)(mean)
    val variance = history.variance(mean)
    println(s"History variance: $variance")
    assertResult(85.2)(variance)
    val standardDeviation = history.standardDeviation(variance)
    println(s"History standard deviation: $standardDeviation")
    assertResult(9.23)(Math.round(standardDeviation * 100d) / 100d)
  }
}
