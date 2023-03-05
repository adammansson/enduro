package io.github.adammansson
package matchers

import parsers.{DriverEntry, TimeEntry}

import scala.collection.mutable

class Matcher(drivers: Vector[DriverEntry], starts: Vector[TimeEntry], ends: Vector[TimeEntry]):

  def result: Vector[MatcherEntry] =
    val numberEntries = mutable.HashMap[Int, MatcherEntry]()

    drivers.foreach(driver =>
      numberEntries.put(driver.number, MatcherEntry(driver.number, Some(driver.name), Vector(), Vector()))
    )

    starts.foreach(start =>
      val numberEntry = numberEntries.get(start.number)
      numberEntries.put(
        start.number,
        MatcherEntry(
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
        MatcherEntry(
          end.number,
          numberEntry.flatMap(_.name),
          numberEntry.map(_.starts).getOrElse(Vector()),
          numberEntry.map(_.ends).getOrElse(Vector()) :+ end.time,
        )
      )
    )

    numberEntries
      .values
      .toVector
      .sortBy(_.number)
