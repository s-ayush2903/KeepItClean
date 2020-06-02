plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
}

android {

    testOptions {
        unitTests(delegateClosureOf<com.android.build.gradle.internal.dsl.TestOptions.UnitTestOptions> {
            isReturnDefaultValues = true
        })
    }


    //not needed as of now
    /*signingConfigs {
        register("release") {
            storeFile = file("keystores/todoapp.keystore")
            storePassword = "toooor"
            keyAlias = "key"
            keyPassword = "toooor"
        }
    }*/


    compileSdkVersion(deps.Versions.compileSdk)
    buildToolsVersion(deps.Versions.buildToolsVersion)

    defaultConfig {
        applicationId = deps.App.id
        minSdkVersion(deps.Versions.minSdk)
        targetSdkVersion(deps.Versions.targetSdk)
        versionCode = 2
        versionName = "1.0"
        testInstrumentationRunner = deps.InstrumentationTestDeps.instrumentationRunner
    }


    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
//            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //Kotlin Libs
    implementation(deps.Dependencies.ktReflect)
    implementation(deps.Dependencies.ktStandardLib)
    implementation(deps.Dependencies.ktx)

    //Support Libs
    implementation(deps.SupportDeps.appCompat)
    implementation(deps.SupportDeps.constraintLayout)
    implementation(deps.SupportDeps.materialDesign)
    implementation(deps.SupportDeps.swipeRefreshLayout)

    //Dagger
    implementation(deps.Dependencies.dagger)
    kapt(deps.kapts.daggerCompiler)

    //Firebase
    implementation(deps.Dependencies.firebaseAnalytics)
    implementation(deps.Dependencies.firebaseAuth)
    implementation(deps.Dependencies.firebaseFirestore)

    //Unit Test Libs
    testImplementation(deps.TestDeps.junit4)

    //Instrumentation Tests Libs (these ain't working right now, will configure 'em while testing)
    //  androidTestImplementation(deps.InstrumentationTestDeps.androidxTestExt)
//    androidTestImplementation(deps.InstrumentationTestDeps.espressoCore)
}