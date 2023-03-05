package io.github.adammansson
package parsers

import scala.io.Source
import scala.util.{Try, Using}

case object TimeParser:
  def parse(filename: String): Vector[TimeEntry] =
    Using(Source.fromFile(filename))(source =>
      source
        .getLines()
        .toVector
        .drop(1)
        .flatMap(line => TimeEntry.from(line.split(";").toVector))
    ).get
