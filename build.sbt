import play.sbt.PlayImport._
import play.sbt.routes.RoutesKeys._

name := """random-repo"""
organization := "com.example"
version := "1.0-SNAPSHOT"
scalaVersion := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
libraryDependencies += filters
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.0.2"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.0.2"
libraryDependencies += "org.apache.hadoop" %  "hadoop-client"   % "2.7.0"
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.7.2"
libraryDependencies += "com.databricks" %% "spark-csv" % "1.5.0"