import common.utils.EnduroTime
import org.scalatest.funsuite.AnyFunSuite
import result.parsers.{DriverEntry, TimeEntry}

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
    assert(testTimeSuccess.exists(_.time == EnduroTime.parse("12:00:00")))
    val testTimeFailure0 = TimeEntry.from(Vector("2", "12"))
    assert(testTimeFailure0.isEmpty)
    val testTimeFailure1 = TimeEntry.from(Vector("Test", "12:00:00"))
    assert(testTimeFailure1.isEmpty)
    val testDriverFailure2 = DriverEntry.from(Vector())
    assert(testDriverFailure2.isEmpty)
  )
