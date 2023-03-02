ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.2"

lazy val root = (project in file("."))
  .settings(
    name := "enduro",
    idePackagePrefix := Some("io.github.adammansson")
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % Test