// https://typelevel.org/sbt-typelevel/faq.html#what-is-a-base-version-anyway
ThisBuild / tlBaseVersion := "0.0" // your current series x.y

ThisBuild / organization := "dev.hnaderi"
ThisBuild / organizationName := "Hossein Naderi"
ThisBuild / startYear := Some(2023)
ThisBuild / licenses := Seq(License.Apache2)
ThisBuild / developers := List(
  // your GitHub handle and name
  tlGitHubDev("hnaderi", "Hossein Naderi")
)

// publish to s01.oss.sonatype.org (set to true to publish to oss.sonatype.org instead)
ThisBuild / tlSonatypeUseLegacyHost := false

// publish website from this branch
ThisBuild / tlSitePublishBranch := Some("main")

val Scala213 = "2.13.10"
ThisBuild / crossScalaVersions := Seq(Scala213, "3.2.1")
ThisBuild / scalaVersion := Scala213 // the default Scala

lazy val root = tlCrossRootProject.aggregate(lib, example)

lazy val lib = crossProject(JVMPlatform, NativePlatform)
  .crossType(CrossType.Full)
  .in(file("lib"))
  .settings(
    name := "readpassword"
  )

lazy val docs = project.in(file("site")).enablePlugins(TypelevelSitePlugin)

lazy val example = crossProject(JVMPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .in(file("example"))
  .dependsOn(lib)
  .enablePlugins(NoPublishPlugin)
