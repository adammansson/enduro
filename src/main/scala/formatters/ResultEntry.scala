package io.github.adammansson
package formatters

import matchers.MatcherEntry

import java.time.{Duration, LocalTime}

case class ResultEntry(
                        number: Int,
                        name: Option[String],
                        starts: Vector[LocalTime],
                        ends: Vector[LocalTime],
                        total: Option[Duration]) extends Ordered[ResultEntry]:
  override def compare(that: ResultEntry): Int =
    (total, that.total) match
      case (Some(myTotal), Some(thatTotal)) => myTotal.compareTo(thatTotal)
      case _ => number.compareTo(that.number)

case object ResultEntry:
  def from(numberEntry: MatcherEntry): ResultEntry =
    val total =
      if numberEntry.starts.length == 1 && numberEntry.ends.length == 1 then
        Some(Duration.between(numberEntry.starts(0), numberEntry.ends(0)))
      else None

    ResultEntry(
      numberEntry.number,
      numberEntry.name,
      numberEntry.starts,
      numberEntry.ends,
      total,
    )
