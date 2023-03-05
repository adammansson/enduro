package io.github.adammansson
package parsers

import scala.io.Source
import scala.util.Using

case object DriverParser:
  def parse(filename: String): Vector[DriverEntry] =
    Using(Source.fromFile(filename))(source =>
      source
        .getLines()
        .toVector
        .drop(1)
        .flatMap(line => DriverEntry.from(line.split(";").toVector))
    ).get
    