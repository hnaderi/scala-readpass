ThisBuild / tlBaseVersion := "0.1"

ThisBuild / organization := "dev.hnaderi"
ThisBuild / organizationName := "Hossein Naderi"
ThisBuild / startYear := Some(2023)
ThisBuild / licenses := Seq(License.Apache2)
ThisBuild / developers := List(
  tlGitHubDev("hnaderi", "Hossein Naderi")
)
ThisBuild / tlSonatypeUseLegacyHost := false

val Scala213 = "2.13.14"
ThisBuild / crossScalaVersions := Seq(Scala213, "3.4.2")
ThisBuild / scalaVersion := Scala213
ThisBuild / githubWorkflowOSes :=
  Seq(
    "ubuntu-20.04",
    "ubuntu-latest",
    "macos-13",
    "macos-latest",
    "windows-2022"
  )
ThisBuild / githubWorkflowAddedJobs += WorkflowJob(
  id = "post-build",
  name = "post build",
  needs = List("build"),
  steps = List(
    WorkflowStep.Run(
      commands = List("echo success!"),
      name = Some("post build")
    )
  ),
  scalas = Nil,
  javas = Nil
)

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
