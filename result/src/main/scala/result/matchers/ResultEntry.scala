package result.matchers

import common.utils.{EnduroDuration, EnduroTime}
import result.formatters.{FormattedEntry, ResultFormatter}

import java.awt.dnd.DragSource
import java.time.{Duration, LocalTime}
import scala.collection.mutable.ArrayBuffer

case class ResultEntry(
                        number: Int,
                        name: Option[String],
                        starts: Vector[EnduroTime],
                        ends: Vector[EnduroTime],
                        total: Option[EnduroDuration],
                        errors: Vector[ResultError],
                      ):
  def convert: FormattedEntry =
    def formatErrors(xs: Vector[ResultError]): String =
      s"<${xs.mkString(ResultFormatter.SEP)}>"

    val formattedNumber: String =
      number.toString

    val formattedName: String =
      NameError.values.filter(error => errors.contains(error)).toVector match
        case xs if xs.nonEmpty => formatErrors(xs)
        case _ => name.get

    val formattedStart: String =
      StartError.values.filter(error => errors.contains(error)).toVector match
        case xs if xs.nonEmpty => formatErrors(xs)
        case _ => starts(0).format

    val formattedEnd: String =
      EndError.values.filter(error => errors.contains(error)).toVector match
        case xs if xs.nonEmpty => formatErrors(xs)
        case _ => ends(0).format

    val formattedTotal: String =
      TotalError.values.filter(error => errors.contains(error)).toVector match
        case xs if xs.nonEmpty => formatErrors(xs)
        case _ => total.get.format

    val formattedErrors: String =
      if errors.nonEmpty then
        s"[${errors.map(_.toString).mkString(ResultFormatter.SEP)}]"
      else
        ""

    FormattedEntry(
      formattedNumber,
      formattedName,
      formattedStart,
      formattedEnd,
      formattedTotal,
      formattedErrors,
    )
