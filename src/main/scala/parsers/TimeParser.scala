package io.github.adammansson
package parsers

import scala.io.Source
import scala.util.{Try, Using}

case object TimeParser:
  def parse(fileName: String): Vector[TimeEntry] =
    Parser.parse[TimeEntry](fileName, line => TimeEntry.from(line.split(";").toVector))
