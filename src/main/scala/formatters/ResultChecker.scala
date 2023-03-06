package io.github.adammansson
package formatters

import scala.collection.mutable.ArrayBuffer

case object ResultChecker:
  def check(resultEntry: ResultEntry): FormattedEntry =
    val errorsFound = ArrayBuffer[String]()

    val checkedName =
      resultEntry.name match
        case Some(name) => name
        case None =>
          val message = "Missing name"
          errorsFound.addOne(message)
          message

    val checkedStart =
      if resultEntry.starts.length == 1 then
        EntryFormatter.time(resultEntry.starts(0))
      else if resultEntry.starts.isEmpty then
        val message = "Start?"
        errorsFound.addOne(message)
        message
      else
        val message = "Multiple starts"
        errorsFound.addOne(message)
        message

    val checkedEnd =
      if resultEntry.ends.length == 1 then
        EntryFormatter.time(resultEntry.ends(0))
      else if resultEntry.ends.isEmpty then
        val message = "End?"
        errorsFound.addOne(message)
        message
      else
        val message = "Multiple ends"
        errorsFound.addOne(message)
        message

    val checkedTotal =
      resultEntry
        .total
        .map(d => EntryFormatter.duration(d))
        .getOrElse("--:--:--")

    FormattedEntry(
      resultEntry.number.toString,
      checkedName,
      checkedStart,
      checkedEnd,
      checkedTotal,
      errorsFound.toVector,
    )
