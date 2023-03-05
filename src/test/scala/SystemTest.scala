package io.github.adammansson

import parsers.{DriverParser, TimeParser}
import matchers.{Matcher, ResultEntry}
import formatters.ResultFormatter

import org.scalatest.funsuite.AnyFunSuite

import java.io.File
import scala.io.Source
import scala.util.Using

class SystemTest extends AnyFunSuite:
  private def vectorFromFile(fileName: String): Vector[String] =
    Using(Source.fromFile(fileName))(source =>
      source
        .getLines()
        .toVector
    ).get

  private def deleteFile(filename: String): Unit = new File(filename).delete()

  test("System")(testFun =
    val expectedResult = vectorFromFile("testdata/result.expected")

    val result = Matcher(
      DriverParser.parse("testdata/drivers.txt"),
      TimeParser.parse("testdata/start.txt"),
      TimeParser.parse("testdata/end.txt"),
    ).result.map(numberEntry => ResultEntry.from(numberEntry))

    val resultFilename = "testdata/result.txt"
    deleteFile(resultFilename)
    ResultFormatter.write(resultFilename, ResultFormatter.format(result))

    val receivedResult = vectorFromFile(resultFilename)
    deleteFile(resultFilename)

    assert(receivedResult == expectedResult)
  )