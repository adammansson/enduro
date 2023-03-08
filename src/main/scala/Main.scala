package io.github.adammansson

import formatters.ResultFormatter
import matchers.{Matcher, ResultEntry}
import parsers.{DriverParser, TimeParser}
import utils.FileUtils

import java.io.File

object Main:
  //TODO: add config file
  def main(args: Array[String]): Unit =
    val result =
      Matcher(
        DriverParser.parse("testdata/drivers.txt"),
        TimeParser.parse("testdata/start.txt"),
        TimeParser.parse("testdata/end.txt"),
      ).result.map(_.convert)

    val resultFilename = "testdata/result.txt"
    FileUtils.deleteFile(resultFilename)
    FileUtils.write(resultFilename, ResultFormatter.format(result))

    println(s"RESULT GENERATED -> saved at $resultFilename")

