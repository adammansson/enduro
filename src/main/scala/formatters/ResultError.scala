package io.github.adammansson
package formatters

sealed trait ResultError

enum NameError extends ResultError:
  case NO_NAME

enum StartError extends ResultError:
  case NO_START
  case MULTIPLE_STARTS

enum EndError extends ResultError:
  case NO_END
  case MULTIPLE_ENDS

enum TotalError extends ResultError:
  case NO_TOTAL
