package io.github.adammansson
package matchers

import entries.{DriverEntry, ResultEntry, TimeEntry}

case object Matcher:
  def result(driverEntries: Vector[DriverEntry], startEntries: Vector[TimeEntry], endEntries: Vector[TimeEntry]): Vector[ResultEntry] =
    driverEntries.map(d => {
      val starts = startEntries.filter(_.number == d.number).map(_.time)
      val ends = endEntries.filter(_.number == d.number).map(_.time)
      ResultEntry(d.number, d.name, starts, ends)
    })
