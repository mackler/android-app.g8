A little different from jberkel's original, tweaked to my preferences.

# Android App in Scala

[giter8](http://github.com/n8han/giter8) template to get an Android
sbt project up and running in a matter of seconds

## How to use

To use this template, g8 needs to be installed first. Read g8's
[readme](http://github.com/n8han/giter8#readme).

All done? Now fire up your favorite shell and enter

    g8 jberkel/android-app
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
