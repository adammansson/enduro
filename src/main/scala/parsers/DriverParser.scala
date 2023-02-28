package io.github.adammansson
package parsers

import entries.DriverEntry

import scala.io.Source

case object DriverParser:
  def parse(filename: String): Vector[DriverEntry] =
    val source = Source.fromFile(filename)
    val driverEntries =
      source
        .getLines()
        .toVector
        .drop(1)
        .map(line => DriverEntry.from(line.split(";").toVector))

    source.close()
    driverEntries
    