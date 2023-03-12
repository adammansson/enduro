package register

import common.utils.{EnduroTime, FileUtils}

import scala.io.StdIn

object Main:
  def main(args: Array[String]): Unit =
    println("Register")

    var running = true
    while running do
      StdIn.readLine.trim.toLowerCase match
        case quitLine if quitLine.startsWith("q") =>
          running = false
        case _ =>
          FileUtils.append("data/start.txt", Vector(EnduroTime.now.format))
