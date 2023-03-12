import common.utils.FileUtils
import org.scalatest.funsuite.AnyFunSuite
import result.formatters.ResultFormatter
import result.matchers.{Matcher, ResultEntry}
import result.parsers.{DriverParser, TimeParser}

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

    val resultFileName = "testdata/result.txt"
    FileUtils.deleteFile(resultFileName)
    FileUtils.overwrite(resultFileName, ResultFormatter.format(result))

    val receivedResult = FileUtils.read(resultFileName)
    assert(receivedResult == expectedResult)
  )