package phi

import org.apache.commons.math3.distribution.NormalDistribution

trait ProbabilityDistributions[T] {
  def cdf(timestamp: Long, samples: History): Double
}
object ProbabilityDistributions {

  implicit val normalDistribution =
    new ProbabilityDistributions[NormalDistribution] {
      override def cdf(timestamp: Long, samples: History): Double = ???
    }
}
