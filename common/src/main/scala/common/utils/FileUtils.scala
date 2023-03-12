package common.utils

import java.io.{BufferedWriter, File, FileWriter}
import scala.io.Source
import scala.util.Using

case object FileUtils:
  def read(fileName: String): Vector[String] =
    Using(Source.fromFile(fileName))(source =>
      source
        .getLines()
        .toVector
    ).get

  def overwrite(fileName: String, rows: Vector[String]): Unit =
    deleteFile(fileName)
    writeToFile(fileName, rows, false)

  def deleteFile(fileName: String): Unit = new File(fileName).delete()

  def append(fileName: String, rows: Vector[String]): Unit =
    writeToFile(fileName, rows, true)

  private def writeToFile(fileName: String, rows: Vector[String], append: Boolean): Unit =
    Using(BufferedWriter(new FileWriter(fileName, append)))(writer =>
      rows.foreach(line =>
        writer.write(line)
        writer.newLine()
      )
    )
