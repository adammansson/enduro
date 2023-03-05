package io.github.adammansson
package formatters

import java.time.format.DateTimeFormatter
import java.time.{Duration, LocalTime}

case object EntryFormatter:
  val timeFormat: DateTimeFormatter = DateTimeFormatter.ISO_TIME

  def time(time: LocalTime): String = time.format(timeFormat)

  def duration(duration: Duration): String = timeFormat.format(duration.addTo(LocalTime.of(0, 0)))