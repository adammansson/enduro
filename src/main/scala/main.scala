package io.github.adammansson

import formatters.Formatter
import matchers.Matcher
import parsers.{DriverParser, TimeParser}

import java.io.File

private def deleteFile(filename: String): Unit =
  new File(filename).delete()

@main
def main(): Unit =
  val driverEntries = DriverParser.parse("testdata/drivers.txt")
  val startTimes = TimeParser.parse("testdata/start.txt")
  val endTimes = TimeParser.parse("testdata/end.txt")
  val matcher = Matcher()
  matcher.addDrivers(driverEntries)
  matcher.addStarts(startTimes)
  matcher.addEnds(endTimes)
  val result = matcher.result

  val resultFilename = "testdata/result.txt"
  deleteFile(resultFilename)
  Formatter.write(resultFilename, Formatter.format(result))
  