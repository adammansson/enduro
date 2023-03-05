package io.github.adammansson
package formatters

import matchers.{MatcherEntry, ResultEntry}

import java.io.{BufferedWriter, FileWriter}
import java.time.format.DateTimeFormatter
import scala.collection.mutable.ArrayBuffer

case object ResultFormatter:
  private val columnNames = "Number; Name; Total; Start; End"
  private val standingsColumnNames = s"Placement; $columnNames"

  def format(result: Vector[ResultEntry]): Vector[String] =
    val formattedLines = ArrayBuffer[String]()

    val validTotals = result.filter(_.total.isDefined)
    formattedLines.append("STANDINGS")
    formattedLines.append(standingsColumnNames)
    val sortedEntries =
      ResultSorter
        .sort(validTotals)
        .zipWithIndex
        .map((resultEntry, placement) => s"${placement + 1}; ${EntryFormatter.entry(resultEntry)}")
    formattedLines.appendAll(sortedEntries)

    formattedLines.append("INVALID TOTALS")
    formattedLines.append(columnNames)
    formattedLines.appendAll(result.diff(validTotals).map(resultEntry => EntryFormatter.entry(resultEntry)))

    formattedLines.toVector

  def write(filename: String, formatted: Vector[String]): Unit =
    val bufferedWriter = new BufferedWriter(new FileWriter(filename))
    formatted.foreach(line =>
      bufferedWriter.write(line)
      bufferedWriter.newLine()
    )
    bufferedWriter.close()