name := """word-scramble"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
	guice,
	jdbc,
    "mysql" % "mysql-connector-java" % "5.1.18"
)

EclipseKeys.preTasks := Seq(compile in Compile)
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java           // Java project. Don't expect Scala IDE
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)
