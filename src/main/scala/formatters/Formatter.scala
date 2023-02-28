package io.github.adammansson
package formatters

import entries.ResultEntry

import java.io.{BufferedWriter, FileWriter}
import java.time.format.DateTimeFormatter

case object Formatter:
  private val SEP = "; "

  def format(result: Vector[ResultEntry]): Vector[String] =
    result.map(re => re.toString)

  def write(filename: String, formatted: Vector[String]): Unit =
    val bufferedWriter = new BufferedWriter(new FileWriter(filename))
    formatted.foreach(line => {
      bufferedWriter.write(line)
      bufferedWriter.newLine()
    })
    bufferedWriter.close()