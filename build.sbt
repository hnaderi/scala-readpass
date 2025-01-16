ThisBuild / tlBaseVersion := "0.2"

ThisBuild / organization := "dev.hnaderi"
ThisBuild / organizationName := "Hossein Naderi"
ThisBuild / startYear := Some(2023)
ThisBuild / licenses := Seq(License.Apache2)
ThisBuild / developers := List(
  tlGitHubDev("hnaderi", "Hossein Naderi")
)

val LTSJava = JavaSpec.temurin("17")
val Scala213 = "2.13.16"
ThisBuild / crossScalaVersions := Seq(Scala213, "3.6.2")
ThisBuild / scalaVersion := Scala213

ThisBuild / tlJdkRelease := Some(17)
ThisBuild / githubWorkflowJavaVersions := Seq(LTSJava)
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
