scalaVersion := "2.13.2"

scalacOptions ++= Seq("-deprecation")

libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-math3" % "3.6.1",
  "dev.zio" %% "zio-nio" % "1.0.0-RC9",
  "org.slf4j" % "slf4j-api" % "1.7.30",
  "org.slf4j" % "slf4j-simple" % "1.7.30",
  "org.scalatest" %% "scalatest" % "3.2.0" % "test"
)
