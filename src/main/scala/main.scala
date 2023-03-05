package io.github.adammansson

import formatters.ResultFormatter
import matchers.Matcher
import parsers.{DriverParser, TimeParser}

import java.io.File

private def deleteFile(filename: String): Unit =
  new File(filename).delete()

@main
def main(): Unit =
  val matcher = Matcher(
    DriverParser.parse("testdata/drivers.txt"),
    TimeParser.parse("testdata/start.txt"),
    TimeParser.parse("testdata/end.txt"),
  )
  val resultFilename = "testdata/result.txt"
  deleteFile(resultFilename)
  ResultFormatter.write(resultFilename, ResultFormatter.format(matcher.result))
  