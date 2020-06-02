import deps.Versions

//const val kotlinVersion = "1.3.71"
//const val buildVersion = "3.6.2"

object BuildPlugins {

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildVersion}"
    const val androidKotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"

    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"

    const val crashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlyticsGradle}"

    //  const val junit5 = "de.mannodermaus.gradle.plugins:android-junit5:1.3.2.0" will import this in a different manner!
}