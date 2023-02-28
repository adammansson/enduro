package io.github.adammansson
package parsers

import entries.TimeEntry

import scala.io.Source

case object TimeParser:
  def parse(filename: String): Vector[TimeEntry] =
    val source = Source.fromFile(filename)
    val driverEntries =
      source
        .getLines()
        .toVector
        .drop(1)
        .map(line => TimeEntry.from(line.split(";").toVector))

    source.close()
    driverEntries
  
