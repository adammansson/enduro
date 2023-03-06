package io.github.adammansson
package formatters

import java.time.format.DateTimeFormatter
import java.time.{Duration, LocalTime}

case object EntryFormatter:
  val timeFormat: DateTimeFormatter = DateTimeFormatter.ISO_TIME
  val SEP: String = "; "

  def time(time: LocalTime): String = time.format(timeFormat)

  // ONLY SUPPORTS DURATIONS OF 24H
  def duration(duration: Duration): String = timeFormat.format(duration.addTo(LocalTime.of(0, 0)))
