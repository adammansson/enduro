package io.github.adammansson
package matchers

import formatters.EntryFormatter

import java.time.format.DateTimeFormatter
import java.time.{Duration, LocalTime}

case class NumberEntry(number: Int, name: Option[String], starts: Vector[LocalTime], ends: Vector[LocalTime]):
  def format: ResultEntry =
    ResultEntry(
      number.toString,
      name.getOrElse("Missing name"),
      starts.headOption.map(s => EntryFormatter.time(s)).getOrElse("Start?"),
      ends.headOption.map(e => EntryFormatter.time(e)).getOrElse("End?"),
      calculateTotal(starts.headOption, ends.headOption).map(t => EntryFormatter.duration(t)).getOrElse("--:--:--")
    )

  private def calculateTotal(start: Option[LocalTime], end: Option[LocalTime]): Option[Duration] =
    (start, end) match
      case (Some(s), Some(e)) => Some(Duration.between(s, e))
      case _ => None
