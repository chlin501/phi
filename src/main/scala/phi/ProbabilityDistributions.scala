package phi

import org.apache.commons.math3.distribution.NormalDistribution

trait ProbabilityDistributions[T] {

  /**
    * Calculate CDF.
    * @param currentTimestamp is the current timestamp.
    * @param samples of historical heartbeats values.
    * @return CDF of sampled heartbeats.
    */
  def cdf(currentTimestamp: Long, samples: History): Double

}
object ProbabilityDistributions {

  def apply[T: ProbabilityDistributions](implicit
      distribution: T
  ): ProbabilityDistributions[T] =
    implicitly[ProbabilityDistributions[T]]

  implicit val normalDistribution =
    new ProbabilityDistributions[NormalDistribution] {

      override def cdf(currentTimestamp: Long, samples: History): Double = {
        val mean = samples.mean
        val variance = samples.variance(mean)
        val standardDeviation = samples.standardDeviation(variance)
        val distribution = new NormalDistribution(mean, standardDeviation)
        val rt = currentTimestamp - samples.latestHeartbeat
        distribution.cumulativeProbability(rt.toDouble)
      }
    }
}
