package result.parsers

import common.utils.EnduroTime

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import scala.util.{Failure, Success, Try}

case class TimeEntry(number: Int, time: EnduroTime)

case object TimeEntry:
  def from(xs: Vector[String]): Option[TimeEntry] =
    if xs.length != 2 then None
    else
      val numberOriginal = xs(0).trim
      val numberTry = Try(numberOriginal.toInt)

      val timeOriginal = xs(1).trim
      val timeTry = Try(EnduroTime.parse(timeOriginal))

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
