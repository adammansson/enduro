package io.github.adammansson
package matchers

import utils.{EnduroDuration, EnduroTime}

import java.time.{Duration, LocalTime}
import scala.collection.mutable.ArrayBuffer

case class MatcherEntry(
                         number: Int,
                         name: Option[String],
                         starts: Vector[EnduroTime],
                         ends: Vector[EnduroTime],
                       ):
  def convert: ResultEntry =
    val errorsFound = ArrayBuffer[ResultError]()

    if name.isEmpty then
      errorsFound.addOne(NameError.NO_NAME)

    if starts.isEmpty then
      errorsFound.addOne(StartError.NO_START)
    else if starts.length > 1 then
      errorsFound.addOne(StartError.MULTIPLE_STARTS)

    if ends.isEmpty then
      errorsFound.addOne(EndError.NO_END)
    else if starts.length > 1 then
      errorsFound.addOne(EndError.MULTIPLE_ENDS)

    val calculatedTotal =
      if !errorsFound.contains(StartError.NO_START) && !errorsFound.contains(EndError.NO_END) then
        Some(EnduroDuration.between(starts(0), ends(0)))
      else
        errorsFound.addOne(TotalError.NO_TOTAL)
        None

    ResultEntry(
      number,
      name,
      starts,
      ends,
      calculatedTotal,
      errorsFound.toVector
    )
