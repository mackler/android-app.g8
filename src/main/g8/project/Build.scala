// required SBT components
import sbt._
import Keys._
import Defaults._

// SBT-Android keys and helper methods
import sbtandroid.AndroidPlugin._

object AndroidBuild extends Build {

  val globalSettings = Seq (
    name := "$name$",
    version := "0.1",
    versionCode := 0,
    scalaVersion := "$scala_version$",
    platformName := "android-$api_level$",
    useProguard := $useProguard$,
    proguardOptions += "@project/proguard.cfg",
    keyalias := "change-me",
    libraryDependencies ++= Seq(
      // uncomment for support library; will increase apk size, require proguard reconfig
      //  aarlib("com.android.support" % "appcompat-v7" % "18.0.0"),
      "org.scalatest" %% "scalatest"     % "$scalatest_version$" % "test",
      "org.slf4j"      % "slf4j-android" % "1.7.6"
    ),
    scalacOptions in Compile ++= Seq(
      "-deprecation","-feature","-language:implicitConversions","-unchecked"
    ),
    javacOptions ++= Seq("-encoding", "UTF-8", "-source", "1.6", "-target", "1.6"),
    javaOptions in Compile += "-Dscalac.patmat.analysisBudget=off",
    initialize ~= { _ ⇒
      sys.props("scalac.patmat.analysisBudget") = "512"
    }
  )

  lazy val main = AndroidProject (
    id       = "$name$",
    base     = file("."),
    settings = globalSettings
  )

  lazy val tests = AndroidTestProject (
    id       = "tests",
    base     = file("tests"),
    settings = globalSettings
  ).
  dependsOn(main % "provided").
  settings(name := "$name$Tests")
}
