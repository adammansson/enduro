package io.github.adammansson
package matchers

import entries.{DriverEntry, ResultEntry, TimeEntry}

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import scala.collection.mutable.ArrayBuffer

class Matcher:
  private val driverEntries = ArrayBuffer[DriverEntry]()
  private val startEntries = ArrayBuffer[TimeEntry]()
  private val endEntries = ArrayBuffer[TimeEntry]()

  def addDrivers(drivers: Vector[DriverEntry]): Unit =
    driverEntries.appendAll(drivers)

  def addStarts(starts: Vector[TimeEntry]): Unit =
    startEntries.appendAll(starts)

  def addEnds(ends: Vector[TimeEntry]): Unit =
    endEntries.appendAll(ends)

  private def filterStarts(driverEntry: DriverEntry, timeEntries: Vector[TimeEntry]): Vector[String] =
    timeEntries.filter(_.number == driverEntry.number) match
      case filtered if filtered.isEmpty => Vector("Start?")
      case filtered => filtered.map(_.time.format(Matcher.timeFormat))

  private def filterEnds(driverEntry: DriverEntry, timeEntries: Vector[TimeEntry]): Vector[String] =
    timeEntries.filter(_.number == driverEntry.number) match
      case filtered if filtered.isEmpty => Vector("Slut?")
      case filtered => filtered.map(_.time.format(Matcher.timeFormat))

  def result: Vector[ResultEntry] =
    driverEntries.toVector.map(driverEntry =>
      val starts = filterStarts(driverEntry, startEntries.toVector)
      val ends = filterEnds(driverEntry, endEntries.toVector)
      ResultEntry(driverEntry.number.toString, driverEntry.name, starts, ends)
    )

object Matcher:
  private val timeFormat = DateTimeFormatter.ISO_TIME
