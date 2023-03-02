package io.github.adammansson
package entries

import java.time.LocalTime
import java.time.format.DateTimeFormatter

case class ResultEntry(number: String, name: String, starts: Vector[String], ends: Vector[String]):
  override def toString: String =
    StringBuilder()
      .append(number)
      .append(ResultEntry.SEP)
      .append(name)
      .append(ResultEntry.SEP)
      .append(starts.mkString(ResultEntry.SEP))
      .append(ResultEntry.SEP)
      .append(ends.mkString(ResultEntry.SEP))
      .append(ResultEntry.SEP)
      .toString

case object ResultEntry:
  private val SEP = "; "