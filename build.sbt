import play.sbt.PlayImport._
import play.sbt.routes.RoutesKeys._

name := """random-repo"""
organization := "com.example"
version := "1.0-SNAPSHOT"
scalaVersion := "2.11.8"
javaOptions in Test += "-Dconfig.file=conf/application.test.conf"
lazy val root = (project in file(".")).enablePlugins(PlayScala)

//spark related
parallelExecution in Test := false
//javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")

libraryDependencies += filters
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0" % Test
libraryDependencies += "com.rockymadden.stringmetric" %% "stringmetric-core" % "0.27.4"

//libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.0.2"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.0.2"
libraryDependencies += "com.holdenkarau" %% "spark-testing-base" % "1.6.1_0.3.3"
libraryDependencies += "org.apache.hadoop" %  "hadoop-client"   % "2.7.0"
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.7.2"
libraryDependencies += "com.databricks" %% "spark-csv" % "1.5.0"
libraryDependencies += "com.adrianhurt" %% "play-bootstrap" % "1.1-P25-B3"