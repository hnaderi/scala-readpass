ThisBuild / tlBaseVersion := "0.0"

ThisBuild / organization := "dev.hnaderi"
ThisBuild / organizationName := "Hossein Naderi"
ThisBuild / startYear := Some(2023)
ThisBuild / licenses := Seq(License.Apache2)
ThisBuild / developers := List(
  tlGitHubDev("hnaderi", "Hossein Naderi")
)
ThisBuild / tlSonatypeUseLegacyHost := false

val Scala213 = "2.13.10"
ThisBuild / crossScalaVersions := Seq(Scala213, "3.2.1")
ThisBuild / scalaVersion := Scala213
ThisBuild / githubWorkflowOSes :=
  Seq("ubuntu-20.04", "ubuntu-22.04", "macos-11", "macos-12", "windows-2022")

lazy val root = tlCrossRootProject.aggregate(lib, example)

lazy val lib = crossProject(JVMPlatform, NativePlatform)
  .crossType(CrossType.Full)
  .in(file("lib"))
  .settings(
    name := "readpassword"
  )

lazy val example = crossProject(JVMPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .in(file("example"))
  .dependsOn(lib)
  .enablePlugins(NoPublishPlugin)
