package io.github.adammansson
package entries

case class DriverEntry(number: Int, name: String)

case object DriverEntry:
  def from(xs: Vector[String]): DriverEntry =
    val number = xs(0).toInt
    val name = xs(1)
    DriverEntry(number, name)