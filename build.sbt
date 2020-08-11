name := "scala-smart-constructors"

version := "0.1"

lazy val scala211Version = "2.11.12"
lazy val scala212Version = "2.12.12"
lazy val scala213Version = "2.13.3"
lazy val dottyVersion    = "0.24.0-RC1"

lazy val commonDependencies =
  libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.2.0" % Test)

lazy val root =
  project
    .in(file("."))
    .aggregate(`root2-11`, `root2-12`, `root2-13`, `rootdotty`)

lazy val `root2-11` = project
  .in(file("2-11"))
  .settings(
    scalaVersion := scala211Version,
    scalacOptions += "-Xsource:2.12",
    commonDependencies
  )

lazy val `root2-12` = project
  .in(file("2-12"))
  .settings(scalaVersion := scala212Version, commonDependencies)

lazy val `root2-13` = project
  .in(file("2-13"))
  .settings(
    scalaVersion := scala213Version,
    scalacOptions += "-Xsource:3",
    commonDependencies
  )

lazy val `rootdotty` = project
  .in(file("dotty"))
  .settings(scalaVersion := dottyVersion, commonDependencies)
