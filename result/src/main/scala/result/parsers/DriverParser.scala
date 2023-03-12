package result.parsers

import scala.io.Source
import scala.util.Using

class DriverParser() extends EntryParser[DriverEntry]:
  override protected def entryFromLine(line: String): Option[DriverEntry] =
    DriverEntry.from(line.split(";").toVector)
    