package result.formatters

import result.matchers.ResultEntry

import java.io.{BufferedWriter, FileWriter}
import java.time.format.DateTimeFormatter
import scala.collection.mutable.ArrayBuffer

case object ResultFormatter:
  val SEP: String = "; "

  def format(result: Vector[ResultEntry]): Vector[String] =
    val standingsColumnNames = "Placement; Number; Name; Start; End; Total"
    val invalidColumnNames = "Number; Name; Start; End; Total; Errors"
    val formattedLines = ArrayBuffer[String]()

    val entriesWithoutErrors = result.filter(_.errors.isEmpty)
    val standings =
      entriesWithoutErrors
        .sorted(Ordering.by(_.total))
        .map(_.convert)
        .zipWithIndex
        .map((formattedEntry, placement) => s"${placement + 1}$SEP${formattedEntry.toString}")

    val entriesWithErrors = result.diff(entriesWithoutErrors)
    val invalid =
      entriesWithErrors
        .sorted(Ordering.by(_.number))
        .map(_.convert)
        .map(_.toString)

    formattedLines
      .append("STANDINGS")
      .append(standingsColumnNames)
      .appendAll(standings)
      .append("INVALID")
      .append(invalidColumnNames)
      .appendAll(invalid)
      .toVector
