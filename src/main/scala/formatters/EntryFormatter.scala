package io.github.adammansson
package formatters

import matchers.ResultEntry

import java.time.format.DateTimeFormatter
import java.time.{Duration, LocalTime}

case object EntryFormatter:
  val timeFormat: DateTimeFormatter = DateTimeFormatter.ISO_TIME

  def time(time: LocalTime): String = time.format(timeFormat)

  def duration(duration: Duration): String = timeFormat.format(duration.addTo(LocalTime.of(0, 0)))

  private val SEP = "; "

  def entry(resultEntry: ResultEntry): String =
    StringBuilder()
      .append(resultEntry.number)
      .append(SEP)
      .append(EntryChecker.checkName(resultEntry.name))
      .append(SEP)
      .append(EntryChecker.checkStart(resultEntry.starts))
      .append(SEP)
      .append(EntryChecker.checkEnd(resultEntry.ends))
      .append(SEP)
      .append(EntryChecker.checkTotal(resultEntry.total))
      .toString

