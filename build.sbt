name := "aws-lambda"

version := "1.0"

scalaVersion := "2.12.1"

retrieveManaged := true

scalacOptions ++= Seq(
  "-feature",
  "-target:jvm-1.8",
  "-encoding", "UTF-8",
  "-unchecked",
  "-deprecation",
  "-Xfuture",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Ywarn-unused"
)


libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.10",
  "com.amazonaws" % "aws-lambda-java-core" % "1.1.0",
  "com.amazonaws" % "aws-lambda-java-events" % "1.3.0"
)


javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

//lazy val root = (project in file(".")).
//  settings(
//    name := "lambda-demo",
//    version := "1.0",
//    scalaVersion := "2.11.4",
//    retrieveManaged := true,
//    libraryDependencies += "com.amazonaws" % "aws-lambda-java-core" % "1.0.0",
//    libraryDependencies += "com.amazonaws" % "aws-lambda-java-events" % "1.0.0"
//  )

assemblyMergeStrategy in assembly := {
  {
    case PathList("META-INF", xs@_*) => MergeStrategy.discard
    case x => MergeStrategy.first
  }
}

