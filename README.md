A little different from jberkel's original, defaults are tweaked to my preferences.

- Default scala version 2.10.1
- Default Android platform is 15
- Default scalac compiler options are:
  - `-deprecation`
  - `-feature`
  - `-language:implicitConversions`
  - `-unchecked`
- Default javac compiler options is `-Dscalac.patmat.analysisBudget=off`
- System property `scalac.patmat.analysisBudget` is set to `512`
- Default Proguard settings:
  - Praguard is configured to read its configuration from file named `project/proguard.cfg`
  - The `project/proguard.cfg` file includes my current idea of an approprate set of defaults,
    in the optimality of which at this time of writing I have rather little confidence.
- By default the `sbt` plugin `ensime-sbt-cmd` is activated (version 0.1.0)

# Android App in Scala

[giter8](http://github.com/n8han/giter8) template to get an Android
sbt project up and running in a matter of seconds

## How to use

To use this template, g8 needs to be installed first. Read g8's
[readme](http://github.com/n8han/giter8#readme).

All done? Now fire up your favorite shell and enter

    g8 mackler/android-app
    cd <name-of-app>
    sbt android:package-debug

## What you get

Your android sbt project contains 2 subprojects:

* MainProject
    * generated AndroidManifest.xml
    * Activity.scala (the "hello world" activity)
* TestProject (tests)
    * Integration tests, see [Android Testing](http://developer.android.com/guide/topics/testing/index.html)

## Installing & Running

    $ sbt
    $ android:emulator-start <tab>
    $ android:install-emulator

## How to run unit tests (src/test/scala/*)

    $ sbt
    > test
    [info] Specs:
    [info] a spec
    [info] - should do something
    [info] Passed: : Total 1, Failed 0, Errors 0, Passed 1, Skipped 0

## How to run integration tests (tests/src/main/scala/*)

The main apk under test needs to be installed first. Then:

    $ sbt
    > project tests
    > android:install-emulator
    > android:test-emulator
    [info]
    [info] my.android.project.tests.ActivityTests:.
    [info] my.android.project.tests.AndroidTests:..
    [info] Test results for InstrumentationTestRunner=...
    [info] Time: 1.492
    [info]
    [info] OK (3 tests)
