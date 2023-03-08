package io.github.adammansson
package formatters

import java.time.format.DateTimeFormatter
import java.time.{Duration, LocalTime}

case class FormattedEntry(number: String, name: String, start: String, end: String, total: String, errors: Vector[String]):
  override def toString: String =
    StringBuilder()
      .append(number)
      .append(ResultFormatter.SEP)
      .append(name)
      .append(ResultFormatter.SEP)
      .append(start)
      .append(ResultFormatter.SEP)
      .append(end)
      .append(ResultFormatter.SEP)
      .append(total)
      .append(formatErrors(errors))
      .toString

  private def formatErrors(errors: Vector[String]): String =
    if errors.isEmpty then "" else s"${ResultFormatter.SEP}[${errors.mkString(ResultFormatter.SEP)}]"

case object FormattedEntry:
  def from(resultEntry: ResultEntry): FormattedEntry =
    val name: String =
      NameError.values.filter(error => resultEntry.errors.contains(error)).toVector match
        case xs if xs.nonEmpty => xs.mkString("")
        case _ => resultEntry.name.get

    val start: String =
      StartError.values.filter(error => resultEntry.errors.contains(error)).toVector match
        case xs if xs.nonEmpty => xs.mkString("")
        case _ => resultEntry.starts(0).format

    val end: String =
      EndError.values.filter(error => resultEntry.errors.contains(error)).toVector match
        case xs if xs.nonEmpty => xs.mkString("")
        case _ => resultEntry.ends(0).format

    val total: String =
      TotalError.values.filter(error => resultEntry.errors.contains(error)).toVector match
        case xs if xs.nonEmpty => xs.mkString("")
        case _ => resultEntry.total.get.format

    FormattedEntry(
      resultEntry.number.toString,
      name,
      start,
      end,
      total,
      resultEntry.errors.map(_.toString),
    )
