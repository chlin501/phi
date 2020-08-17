package phi

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

  def mean: Double = heartbeats.sum.toDouble / heartbeats.length

  def latestHeartbeat: Long =
    heartbeats match {
      case Nil => 0L
      case _   => heartbeats.head
    }

}
