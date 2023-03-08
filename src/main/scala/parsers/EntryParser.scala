package io.github.adammansson
package parsers

import scala.io.Source
import scala.util.Using

case object EntryParser:
  def parse[A](fileName: String, entryFromLine: String => Option[A]): Vector[A] =
    Using(Source.fromFile(fileName))(source =>
      source
        .getLines()
        .toVector
        .drop(1)
        .flatMap(line =>
          if line.startsWith("#") then
            None
          else
            entryFromLine(line)
        )
    ).get

