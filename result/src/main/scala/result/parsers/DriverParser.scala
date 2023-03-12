package result.parsers

import scala.io.Source
import scala.util.Using

case object DriverParser:
  def parse(fileName: String): Vector[DriverEntry] =
    EntryParser.parse[DriverEntry](fileName, line => DriverEntry.from(line.split(";").toVector))
    