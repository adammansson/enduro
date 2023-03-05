package io.github.adammansson
package matchers

import java.time.format.DateTimeFormatter
import java.time.{Duration, LocalTime}

case class ResultEntry(number: String, name: String, start: String, ends: String, total: String):
  override def toString: String =
    StringBuilder()
      .append(number)
      .append(ResultEntry.SEP)
      .append(name)
      .append(ResultEntry.SEP)
      .append(start)
      .append(ResultEntry.SEP)
      .append(ends)
      .append(ResultEntry.SEP)
      .append(total)
      .toString

case object ResultEntry:
  private val SEP = "; "
  