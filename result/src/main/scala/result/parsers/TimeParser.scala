package result.parsers

import scala.io.Source
import scala.util.{Try, Using}

class TimeParser() extends EntryParser[TimeEntry]:
  override protected def entryFromLine(line: String): Option[TimeEntry] =
    TimeEntry.from(line.split(";").toVector)
