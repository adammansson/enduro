package io.github.adammansson
package matchers

import parsers.{DriverEntry, TimeEntry}

import scala.collection.mutable

class Matcher(drivers: Vector[DriverEntry], starts: Vector[TimeEntry], ends: Vector[TimeEntry]):

  def result: Vector[MatcherEntry] =
    val matcherEntries = mutable.HashMap[Int, MatcherEntry]()

    drivers.foreach(driver =>
      matcherEntries.put(driver.number, MatcherEntry(driver.number, Some(driver.name), Vector(), Vector()))
    )

    starts.foreach(start =>
      val matcherEntry = matcherEntries.get(start.number)
      matcherEntries.put(
        start.number,
        MatcherEntry(
          start.number,
          matcherEntry.flatMap(_.name),
          matcherEntry.map(_.starts).getOrElse(Vector()) :+ start.time,
          matcherEntry.map(_.ends).getOrElse(Vector()),
        )
      )
    )

    ends.foreach(end =>
      val matcherEntry = matcherEntries.get(end.number)
      matcherEntries.put(
        end.number,
        MatcherEntry(
          end.number,
          matcherEntry.flatMap(_.name),
          matcherEntry.map(_.starts).getOrElse(Vector()),
          matcherEntry.map(_.ends).getOrElse(Vector()) :+ end.time,
        )
      )
    )

    matcherEntries
      .values
      .toVector
      .sortBy(_.number)
