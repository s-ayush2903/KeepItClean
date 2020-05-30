package dependencies

/** Build Support Deps **/

object Build {

  const val buildTools = "com.android.tools.build:gradle:${Versions.gradle}"
  const val ktGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
  const val googleServices = "com.google.gms:google-services:${Versions.playServices}"
//  const val junit5 = "de.mannodermaus.gradle.plugins:android-junit5:1.3.2.0" will import this in a different manner!
  const val crashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlyticsGradle}"

}