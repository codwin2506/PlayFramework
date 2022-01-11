import sbt.util.Cache.cache

name := """API-Playframework"""
organization := "com.knoldus"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.7"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.knoldus.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.knoldus.binders._"

libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.25"
libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.0.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0"



libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "42.3.1",
  "com.typesafe.slick" %% "slick" % "3.3.3",
 // "org.slf4j" % "slf4j-nop" % "1.7.32"
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",

  "org.postgresql" % "postgresql" % "42.3.1",
  "com.typesafe.slick" %% "slick" % "3.3.3",
  //"org.slf4j" % "slf4j-nop" % "1.7.32"
)

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "42.3.1",
  "com.typesafe.slick" %% "slick" % "3.3.3",
 // "org.slf4j" % "slf4j-nop" % "1.7.32"
)


