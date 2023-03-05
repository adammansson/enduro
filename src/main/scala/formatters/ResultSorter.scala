package io.github.adammansson
package formatters

case object ResultSorter:
  def sort(resultEntries: Vector[ResultEntry]): Vector[ResultEntry] =
    resultEntries.sorted
