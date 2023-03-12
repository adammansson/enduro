package result

import common.utils.FileUtils
import result.formatters.ResultFormatter
import result.matchers.Matcher
import result.parsers.{DriverParser, TimeParser}

object Main:
  def main(args: Array[String]): Unit =
    //TODO: add config file
    val result =
      Matcher(
        DriverParser().parse("testdata/drivers.txt"),
        TimeParser().parse("testdata/start.txt"),
        TimeParser().parse("testdata/end.txt"),
      ).result.map(_.convert)

    val resultFilename = "testdata/result.txt"
    FileUtils.deleteFile(resultFilename)
    FileUtils.overwrite(resultFilename, ResultFormatter.format(result))

    println(s"RESULT GENERATED -> saved at $resultFilename")
