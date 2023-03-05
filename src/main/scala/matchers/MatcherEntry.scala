package io.github.adammansson
package matchers

import java.time.{Duration, LocalTime}

case class MatcherEntry(number: Int, name: Option[String], starts: Vector[LocalTime], ends: Vector[LocalTime])