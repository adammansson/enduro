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
    else if ends.length > 1 then
      errorsFound.addOne(EndError.MULTIPLE_ENDS)

    val calculatedTotal =
      if starts.isEmpty || ends.isEmpty then
        errorsFound.addOne(TotalError.NO_TOTAL)
        None
      else if starts.length > 1 || ends.length > 1 then
        errorsFound.addOne(TotalError.UNKNOWN_TOTAL)
        None
      else
        Some(EnduroDuration.between(starts(0), ends(0)))

    ResultEntry(
      number,
      name,
      starts,
      ends,
      calculatedTotal,
      errorsFound.toVector
    )
