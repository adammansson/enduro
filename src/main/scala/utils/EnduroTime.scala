package io.github.adammansson
package utils

import java.time.LocalTime
import java.time.format.DateTimeFormatter

case class EnduroTime(time: LocalTime):
  def format: String =
    time.format(DateTimeFormatter.ISO_LOCAL_TIME)

case object EnduroTime:
  def parse(text: String): EnduroTime =
    EnduroTime(LocalTime.parse(text, DateTimeFormatter.ISO_LOCAL_TIME))
