package io.github.adammansson
package formatters

import matchers.ResultEntry

import java.io.{BufferedWriter, FileWriter}
import java.time.format.DateTimeFormatter

case object ResultFormatter:
  private val columnNames = "Number; Name; Total; Start; End"

  def format(result: Vector[ResultEntry]): Vector[String] =
    columnNames +: result.map(_.toString)

  def write(filename: String, formatted: Vector[String]): Unit =
    val bufferedWriter = new BufferedWriter(new FileWriter(filename))
    formatted.foreach(line =>
      bufferedWriter.write(line)
      bufferedWriter.newLine()
    )
    bufferedWriter.close()