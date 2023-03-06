package io.github.adammansson
package parsers

import scala.io.Source
import scala.util.Using

case object Parser:
  def parse[A](fileName: String, createEntryFromLine: String => Option[A]): Vector[A] =
    Using(Source.fromFile(fileName))(source =>
      source
        .getLines()
        .toVector
        .flatMap(line =>
          if line.startsWith("#") then
            None
          else
            createEntryFromLine(line)
        )
    ).get

