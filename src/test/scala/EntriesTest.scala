package io.github.adammansson

import entries.{DriverEntry, TimeEntry}

import org.scalatest.funsuite.AnyFunSuite

import java.time.LocalTime
import java.time.format.DateTimeFormatter

class EntriesTest extends AnyFunSuite:
  test("DriverEntry.from")(testFun =
    val testDriverSuccess = DriverEntry.from(Vector("1", "Test"))
    assert(testDriverSuccess.exists(_.number == 1))
    assert(testDriverSuccess.exists(_.name == "Test"))
    val testDriverFailure = DriverEntry.from(Vector("Test", "Test"))
    assert(testDriverFailure.isEmpty)
  )

  test("TimeEntry.from")(testFun =
    val testTimeSuccess = TimeEntry.from(Vector("1", "12:00:00"))
    assert(testTimeSuccess.exists(_.number == 1))
    assert(testTimeSuccess.exists(_.time == LocalTime.parse("12:00:00", DateTimeFormatter.ISO_TIME)))
    val testTimeFailure0 = TimeEntry.from(Vector("2", "12"))
    assert(testTimeFailure0.isEmpty)
    val testTimeFailure1 = TimeEntry.from(Vector("Test", "12:00:00"))
    assert(testTimeFailure1.isEmpty)
  )

