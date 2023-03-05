package io.github.adammansson
package formatters

import matchers.ResultEntry

case object ResultSorter:
  def sort(resultEntries: Vector[ResultEntry]): Vector[ResultEntry] =
    resultEntries.sorted
