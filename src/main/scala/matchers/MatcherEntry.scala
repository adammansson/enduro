package io.github.adammansson
package matchers

import utils.EnduroTime

import java.time.{Duration, LocalTime}

case class MatcherEntry(number: Int, name: Option[String], starts: Vector[EnduroTime], ends: Vector[EnduroTime])