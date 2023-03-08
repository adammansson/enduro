package io.github.adammansson

import formatters.ResultFormatter
import matchers.{Matcher, ResultEntry}
import parsers.{DriverParser, TimeParser}
import utils.FileUtils

import org.scalatest.funsuite.AnyFunSuite

import java.io.File
import scala.io.Source
import scala.util.Using

class SystemTest extends AnyFunSuite:

  test("System")(testFun =
    val expectedResult = FileUtils.read("testdata/result.expected")

    val result = Matcher(
      DriverParser.parse("testdata/drivers.txt"),
      TimeParser.parse("testdata/start.txt"),
      TimeParser.parse("testdata/end.txt"),
    ).result.map(_.convert)

    val resultFilename = "testdata/result.txt"
    FileUtils.deleteFile(resultFilename)
    FileUtils.write(resultFilename, ResultFormatter.format(result))

    val receivedResult = FileUtils.read(resultFilename)
    assert(receivedResult == expectedResult)
  )