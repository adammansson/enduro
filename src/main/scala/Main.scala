package io.github.adammansson

import formatters.ResultFormatter
import matchers.{Matcher, ResultEntry}
import parsers.{DriverParser, TimeParser}

import java.io.File

object Main:
  private def deleteFile(filename: String): Unit = new File(filename).delete()
  def main(args: Array[String]): Unit =
    val result = Matcher(
      DriverParser.parse("testdata/drivers.txt"),
      TimeParser.parse("testdata/start.txt"),
      TimeParser.parse("testdata/end.txt"),
    ).result.map(numberEntry => ResultEntry.from(numberEntry))

    val resultFilename = "testdata/result.txt"
    deleteFile(resultFilename)
    ResultFormatter.write(resultFilename, ResultFormatter.format(result))

    println(s"RESULT GENERATED -> at $resultFilename")
