package io.github.adammansson
package entries

import java.time.LocalTime
import java.time.format.DateTimeFormatter

case class ResultEntry(number: Int, name: String, starts: Vector[LocalTime], ends: Vector[LocalTime]):
  override def toString: String =
    val sb = StringBuilder()

    sb.append(number)
    sb.append(ResultEntry.SEP)

    sb.append(name)
    sb.append(ResultEntry.SEP)

    sb.append(formatTimes(starts))
    sb.append(ResultEntry.SEP)

    sb.append(formatTimes(ends))
    sb.append(ResultEntry.SEP)

    sb.toString

  private def formatTimes(times: Vector[LocalTime]): String =
    times.map(_.format(ResultEntry.timeFormat)).mkString(ResultEntry.SEP)

case object ResultEntry:
  private val SEP = "; "
  private val timeFormat = DateTimeFormatter.ISO_TIME