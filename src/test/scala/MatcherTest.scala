package io.github.adammansson

import formatters.ResultFormatter
import matchers.{Matcher, MatcherEntry}
import parsers.{DriverParser, TimeParser}
import utils.EnduroTime

import org.scalatest.funsuite.AnyFunSuite

import java.time.{Duration, LocalTime}

class MatcherTest extends AnyFunSuite:
  test("Matcher.result")(testFun =
    val result =
      Matcher(
        DriverParser.parse("testdata/drivers.txt"),
        TimeParser.parse("testdata/start.txt"),
        TimeParser.parse("testdata/end.txt"),
      ).result

    val matcherEntry0 =
      MatcherEntry(
        1,
        Some("Anders Asson"),
        Vector(EnduroTime.parse("17:33:49")),
        Vector(EnduroTime.parse("18:01:37")),
      )

    val matcherEntry3 =
      MatcherEntry(
        4,
        None,
        Vector(EnduroTime.parse("17:33:40")),
        Vector(EnduroTime.parse("18:02:12")),
      )

    assert(result(0) == matcherEntry0)
    assert(result(3) == matcherEntry3)
  )
