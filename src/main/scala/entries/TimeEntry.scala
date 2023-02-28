package io.github.adammansson
package entries

import java.time.LocalTime

case class TimeEntry(number: Int, time: LocalTime)

case object TimeEntry:
  def from(xs: Vector[String]): TimeEntry =
    val number = xs(0).toInt
    val time = LocalTime.parse(xs(1))
    TimeEntry(number, time)
