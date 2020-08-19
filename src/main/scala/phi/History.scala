package phi

object History {

  def empty = History()

}

/**
  * Heartbeat history.
  * @param heartbeats contains heartbeat values where the most left hand side is the newest heartbeat,
  *                   whereas the right hand side is the oldest.
  * @param window is the sample window size; default to 100.
  */
protected[phi] case class History(
    heartbeats: Seq[Long] = Seq.empty[Long],
    window: Int = 100
) {

  /**
    * Prepend heartbeat to the seq.
    * @param heartbeat is the newest timestamp value
    * @return History that contains the latest heartbeat
    */
  def +:(heartbeat: Long): History = {
    val newHeartbeats = heartbeat +: heartbeats
    if (newHeartbeats.size > window) {
      val dropSize = newHeartbeats.size - window
      History(newHeartbeats.dropRight(dropSize), window)
    } else History(newHeartbeats, window)
  }

  /**
    * The mean value of heartbeats interval.
    * @return mean of heartbeats interval.
    */
  def mean: Double = intervals.sum.toDouble / intervals.length

  /**
    * Variance of heartbeats interval.
    * @param mean calculated in [[History.mean]].
    * @return variance value.
    */
  def variance(mean: Double): Double =
    intervals.foldLeft(0.0) {
      case (acc, heartbeat) =>
        val x = (heartbeat - mean)
        val power = x * x
        acc + power
    } / (intervals.size - 1)

  /**
    * Standard deviation derived from [[History.variance]]
    * @param variance
    * @return
    */
  def standardDeviation(variance: Double): Double = Math.sqrt(variance)

  /**
    * Latest heartbeat value.
    * @return if empty, returns 0L; otherwise the latest (the left most) heartbeat value.
    */
  def latestHeartbeat: Long =
    heartbeats match {
      case Nil => 0L
      case _   => heartbeats.head
    }

  /**
    * Intervals of heartbeats collected. For instance, suppose collected heartbeats sequence is
    * {{{ Seq(20, 17, 15, 13, 11, 10) }}}. Its intervals sequence is {{{ Seq(3, 2, 2, 2, 1) }}}.
    * @return a sequence of heartbeat intervals.
    */
  def intervals: Seq[Long] =
    heartbeats.sliding(size = 2, step = 1).toSeq.map { seq => seq(0) - seq(1) }

}
