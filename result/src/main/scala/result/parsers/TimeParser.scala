package result.parsers

import scala.io.Source
import scala.util.{Try, Using}

case object TimeParser:
  def parse(fileName: String): Vector[TimeEntry] =
    EntryParser.parse[TimeEntry](fileName, line => TimeEntry.from(line.split(";").toVector))
