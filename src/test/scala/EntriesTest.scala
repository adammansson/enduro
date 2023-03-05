package io.github.adammansson

import formatters.EntryFormatter
import matchers.ResultEntry
import parsers.{DriverEntry, TimeEntry}

import org.scalatest.funsuite.AnyFunSuite

import java.time.format.DateTimeFormatter
import java.time.{Duration, LocalTime}

class EntriesTest extends AnyFunSuite:
  test("DriverEntry.from")(testFun =
    val testDriverSuccess = DriverEntry.from(Vector("1", "Test"))
    assert(testDriverSuccess.exists(_.number == 1))
    assert(testDriverSuccess.exists(_.name == "Test"))
    val testDriverFailure0 = DriverEntry.from(Vector("Test", "Test"))
    assert(testDriverFailure0.isEmpty)
    val testDriverFailure1 = DriverEntry.from(Vector())
    assert(testDriverFailure1.isEmpty)
  )

  test("TimeEntry.from")(testFun =
    val testTimeSuccess = TimeEntry.from(Vector("1", "12:00:00"))
    assert(testTimeSuccess.exists(_.number == 1))
    assert(testTimeSuccess.exists(_.time == LocalTime.parse("12:00:00", EntryFormatter.timeFormat)))
    val testTimeFailure0 = TimeEntry.from(Vector("2", "12"))
    assert(testTimeFailure0.isEmpty)
    val testTimeFailure1 = TimeEntry.from(Vector("Test", "12:00:00"))
    assert(testTimeFailure1.isEmpty)
    val testDriverFailure2 = DriverEntry.from(Vector())
    assert(testDriverFailure2.isEmpty)
  )

  test("ResultEntry.toString")(testFun =
    val number = "1"
    val name = "Test"
    val start = "12:00:00"
    val end = "13:00:00"
    val total = "1:00:00"
    val testResult = ResultEntry(number, name, start, end, total)
    assert(testResult.toString == "1; Test; 12:00:00; 13:00:00; 1:00:00")
  )

