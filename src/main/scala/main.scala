package io.github.adammansson

import formatters.Formatter
import matchers.Matcher
import parsers.{DriverParser, TimeParser}

@main
def main(): Unit = {
  val driverEntries = DriverParser.parse("testdata/drivers.txt")
  val startTimes = TimeParser.parse("testdata/start.txt")
  val endTimes = TimeParser.parse("testdata/end.txt")
  val result = Matcher.result(driverEntries, startTimes, endTimes)
  val formatted = Formatter.format(result)
  Formatter.write("testdata/result.txt", formatted)
}