package io.github.adammansson
package formatters

import java.time.{Duration, LocalTime}

case object EntryChecker:
  def checkName(name: Option[String]): String =
    name.getOrElse("<Missing name>")

  def checkStart(starts: Vector[LocalTime]): String =
    if starts.length == 1 then
      EntryFormatter.time(starts(0))
    else
      "Start?"

  def checkEnd(ends: Vector[LocalTime]): String =
    if ends.length == 1 then
      EntryFormatter.time(ends(0))
    else
      "End?"

  def checkTotal(total: Option[Duration]): String =
    total.map(d => EntryFormatter.duration(d)).getOrElse("--:--:--")