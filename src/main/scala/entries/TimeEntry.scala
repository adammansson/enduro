package io.github.adammansson
package entries

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import scala.util.{Failure, Success, Try}

case class TimeEntry(number: Int, time: LocalTime)

case object TimeEntry:
  private val timeFormat = DateTimeFormatter.ISO_TIME

  def from(xs: Vector[String]): Option[TimeEntry] =
    val numberOriginal = xs(0).trim
    val numberTry = Try(numberOriginal.toInt)

    val timeOriginal = xs(1).trim
    val timeTry = Try(LocalTime.parse(timeOriginal, timeFormat))

    (numberTry, timeTry) match
      case (Success(number), Success(time)) => Some(TimeEntry(number, time))
      case (Failure(_), Success(_)) =>
        Console.err.println(s"Error parsing '$numberOriginal' as int")
        None
      case (Success(_), Failure(_)) =>
        Console.err.println(s"Error parsing '$timeOriginal' as ISO_TIME")
        None
      case _ =>
        Console.err.println(s"Error parsing '$numberOriginal' as int")
        Console.err.println(s"Error parsing '$timeOriginal' as ISO_TIME")
        None
