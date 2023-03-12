package result.parsers

import common.utils.FileUtils

import scala.io.Source
import scala.util.Using

abstract class EntryParser[A]():
  def parse(fileName: String): Vector[A] =
    FileUtils.read(fileName)
      .flatMap(line =>
        line match
          case comment if comment.startsWith("#") => None
          case line => entryFromLine(line)
      )

  protected def entryFromLine(line: String): Option[A]
