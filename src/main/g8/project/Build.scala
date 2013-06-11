import sbt._

import Keys._
import sbtandroid.AndroidKeys._

object General {
  val settings = Defaults.defaultSettings ++ Seq (
    name := "$name$",
    version := "0.1",
    versionCode := 0,
    scalaVersion := "$scala_version$",
    platformName in Android := "android-$api_level$",
    scalacOptions in Compile ++= Seq("-deprecation","-feature","-language:implicitConversions","-unchecked"),
    javaOptions in Compile += "-Dscalac.patmat.analysisBudget=off",
    initialize ~= { _ ⇒
      sys.props("scalac.patmat.analysisBudget") = "512"
    }
  )

  val proguardSettings = Seq (
    useProguard in Android := $useProguard$,
    proguardOption in Android := "@project/proguard.cfg"
  )

  lazy val fullAndroidSettings =
    General.settings ++
    sbtandroid.AndroidProject.androidSettings ++
    sbtandroid.TypedResources.settings ++
    proguardSettings ++
    sbtandroid.AndroidManifestGenerator.settings ++
    sbtandroid.AndroidMarketPublish.settings ++ Seq (
      keyalias in Android := "change-me",
      libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest"     % "$scalatest_version$" % "test",
        "org.slf4j"      % "slf4j-android" % "1.6.1-RC1"
      )
    )
}

object AndroidBuild extends Build {
  lazy val main = Project (
    id = "$name$",
    base = file("."),
    settings = General.fullAndroidSettings
  )

  lazy val tests = Project (
    id = "tests",
    base = file("tests"),
    settings = General.settings ++
               AndroidTest.androidSettings ++
               General.proguardSettings ++ Seq (
      name := "$name$Tests"
    )
  ) dependsOn main
}
