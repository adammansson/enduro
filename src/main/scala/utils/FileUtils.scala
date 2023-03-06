package io.github.adammansson
package utils

import java.io.{BufferedWriter, File, FileWriter}
import scala.io.Source
import scala.util.Using

case object FileUtils:
  def deleteFile(filename: String): Unit = new File(filename).delete()

  def read(fileName: String): Vector[String] =
    Using(Source.fromFile(fileName))(source =>
      source
        .getLines()
        .toVector
    ).get

  def write(filename: String, rows: Vector[String]): Unit =
    val bufferedWriter = new BufferedWriter(new FileWriter(filename))
    rows.foreach(line =>
      bufferedWriter.write(line)
      bufferedWriter.newLine()
    )
    bufferedWriter.close()
