package io.github.adammansson
package utils

import java.time.format.DateTimeFormatter
import java.time.{Duration, LocalTime}

case class EnduroDuration(duration: Duration) extends Ordered[EnduroDuration]:
  def format: String =
    DateTimeFormatter.ISO_LOCAL_TIME.format(duration.addTo(LocalTime.of(0, 0)))

  override def compare(that: EnduroDuration): Int = duration.compareTo(that.duration)

case object EnduroDuration:
  def between(start: EnduroTime, end: EnduroTime): EnduroDuration =
    EnduroDuration(Duration.between(start.time, end.time))
