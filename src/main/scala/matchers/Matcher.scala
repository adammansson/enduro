package io.github.adammansson
package matchers

import parsers.{DriverEntry, TimeEntry}

import scala.collection.mutable

class Matcher(drivers: Vector[DriverEntry], starts: Vector[TimeEntry], ends: Vector[TimeEntry]):
  private val numberEntries = mutable.HashMap[Int, NumberEntry]()

  def result: Vector[ResultEntry] =
    addDrivers()

    starts.foreach(start =>
      val numberEntry = numberEntries.get(start.number)
      numberEntries.put(
        start.number,
        NumberEntry(
          start.number,
          numberEntry.flatMap(_.name),
          numberEntry.map(_.starts).getOrElse(Vector()) :+ start.time,
          numberEntry.map(_.ends).getOrElse(Vector()),
        )
      )
    )

    ends.foreach(end =>
      val numberEntry = numberEntries.get(end.number)
      numberEntries.put(
        end.number,
        NumberEntry(
          end.number,
          numberEntry.flatMap(_.name),
          numberEntry.map(_.starts).getOrElse(Vector()),
          numberEntry.map(_.ends).getOrElse(Vector()) :+ end.time,
        )
      )
    )

    numberEntries.values.toVector.map(_.format)

  private def addDrivers(): Unit =
    drivers.foreach(d => numberEntries.put(d.number, NumberEntry(d.number, Some(d.name), Vector(), Vector())))
    