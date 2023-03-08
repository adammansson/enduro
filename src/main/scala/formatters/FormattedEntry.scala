package io.github.adammansson
package formatters

import matchers.{ResultEntry, ResultError}

import java.time.format.DateTimeFormatter
import java.time.{Duration, LocalTime}

case class FormattedEntry(
                           number: String,
                           name: String,
                           start: String,
                           end: String,
                           total: String,
                           errors: String,
                         ):
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
      .append(ResultFormatter.SEP)
      .append(errors)
      .toString
