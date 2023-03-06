package io.github.adammansson
package formatters

import matchers.MatcherEntry

import java.io.{BufferedWriter, FileWriter}
import java.time.format.DateTimeFormatter
import scala.collection.mutable.ArrayBuffer

case object ResultFormatter:
  private val standingsColumnNames = "Placement; Number; Name; Start; End; Total"
  private val invalidColumnNames = "Number; Name; Start; End; Total; Errors"

  def format(result: Vector[ResultEntry]): Vector[String] =
    val formattedLines = ArrayBuffer[String]()

    val sortedResult =
      ResultSorter
        .sort(result)
        .map(resultEntry => ResultChecker.check(resultEntry))
    val entriesWithoutErrors = sortedResult.filter(_.errors.isEmpty)

    formattedLines.append("STANDINGS")
    formattedLines.append(standingsColumnNames)
    val standings =
      entriesWithoutErrors
        .zipWithIndex
        .map((formattedEntry, placement) => s"${placement + 1}; ${formattedEntry.toString}")
    formattedLines.appendAll(standings)

    formattedLines.append("INVALID")
    formattedLines.append(invalidColumnNames)
    formattedLines.appendAll(sortedResult.diff(entriesWithoutErrors).map(_.toString))

    formattedLines.toVector
