package io.github.adammansson
package formatters

import matchers.MatcherEntry
import utils.{EnduroDuration, EnduroTime}

import java.awt.dnd.DragSource
import java.time.{Duration, LocalTime}
import scala.collection.mutable.ArrayBuffer

case class ResultEntry(
                        number: Int,
                        name: Option[String],
                        starts: Vector[EnduroTime],
                        ends: Vector[EnduroTime],
                        total: Option[EnduroDuration],
                        errors: Vector[ResultError],
                      )

case object ResultEntry:
  def from(matcherEntry: MatcherEntry): ResultEntry =
    val errorsFound = ArrayBuffer[ResultError]()

    if matcherEntry.name.isEmpty then
      errorsFound.addOne(NameError.NO_NAME)

    if matcherEntry.starts.isEmpty then
      errorsFound.addOne(StartError.NO_START)
    else if matcherEntry.starts.length > 1 then
      errorsFound.addOne(StartError.MULTIPLE_STARTS)

    if matcherEntry.ends.isEmpty then
      errorsFound.addOne(EndError.NO_END)
    else if matcherEntry.starts.length > 1 then
      errorsFound.addOne(EndError.MULTIPLE_ENDS)

    val calculatedTotal =
      if !errorsFound.contains(StartError.NO_START) && !errorsFound.contains(EndError.NO_END) then
        Some(EnduroDuration.between(matcherEntry.starts(0), matcherEntry.ends(0)))
      else
        errorsFound.addOne(TotalError.NO_TOTAL)
        None

    ResultEntry(
      matcherEntry.number,
      matcherEntry.name,
      matcherEntry.starts,
      matcherEntry.ends,
      calculatedTotal,
      errorsFound.toVector
    )
