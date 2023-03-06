package io.github.adammansson
package formatters

import java.time.{Duration, LocalTime}

case class FormattedEntry(number: String, name: String, start: String, end: String, total: String, errors: Vector[String]):
  override def toString: String =
    StringBuilder()
      .append(number)
      .append(EntryFormatter.SEP)
      .append(name)
      .append(EntryFormatter.SEP)
      .append(start)
      .append(EntryFormatter.SEP)
      .append(end)
      .append(EntryFormatter.SEP)
      .append(total)
      .append(EntryFormatter.SEP)
      .append(formatErrors(errors))
      .toString

  private def formatErrors(errors: Vector[String]): String =
    if errors.isEmpty then "" else s"[${errors.mkString(EntryFormatter.SEP)}]"
