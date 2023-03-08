package io.github.adammansson

import formatters.{ResultEntry, ResultFormatter}
import matchers.Matcher
import parsers.{DriverParser, TimeParser}
import utils.FileUtils

import java.io.File

object Main:
  def main(args: Array[String]): Unit =
    val result = Matcher(
      DriverParser.parse("testdata/drivers.txt"),
      TimeParser.parse("testdata/start.txt"),
      TimeParser.parse("testdata/end.txt"),
    ).result.map(matcherEntry => ResultEntry.from(matcherEntry))

    val resultFilename = "testdata/result.txt"
    FileUtils.deleteFile(resultFilename)
    FileUtils.write(resultFilename, ResultFormatter.format(result))

    println(s"RESULT GENERATED -> saved at $resultFilename")

