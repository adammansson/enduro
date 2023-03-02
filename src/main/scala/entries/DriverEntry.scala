package io.github.adammansson
package entries

import scala.util.{Failure, Success, Try}

case class DriverEntry(number: Int, name: String)

case object DriverEntry:
  def from(xs: Vector[String]): Option[DriverEntry] =
    val numberOriginal = xs(0).trim
    val numberTry = Try(numberOriginal.toInt)

    val name = xs(1).trim

    numberTry match
      case Success(number) => Some(DriverEntry(number, name))
      case Failure(_) =>
        Console.err.println(s"Error parsing '$numberOriginal' as int")
        None
