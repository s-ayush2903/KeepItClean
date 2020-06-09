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

//    packagingOptions {
//        exclude ("META-INF/DEPENDENCIES")
//    }
    /* Fix this, its failing ./gradlew testDebug
    testOptions {
        unitTests.all(KotlinClosure1<Any, Test>({
            (this as Test).also { useJUnitPlatform() }
        }, unitTests))
    }*/

    /** useless though */
//    testOptions {
//        unitTests.all(KotlinClosure1<Any, Test>({
//            (this as Test).also { jvmArgs("-noverify") }
//        }, unitTests))
//    }

}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //Kotlin Libs
    implementation(deps.Dependencies.ktReflect)
    implementation(deps.Dependencies.ktStandardLib)
    implementation(deps.Dependencies.ktx)

    //Kotlin CoRoutines
    implementation(deps.Dependencies.ktCoRoutines)
    implementation(deps.Dependencies.ktCoRoutinesAndroid)
    implementation(deps.Dependencies.ktCoRoutinesPlayServices)   //it returns coRoutines from firestore tasks

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
    implementation(deps.Dependencies.firebaseCrashlytics)

    //Unit Test Libs
    testImplementation(BuildPlugins.junit5)  //actually, i wanna implement it new way, as i've done for ktOptions, but it threw exceptions for testDebug, so check if it could be done in future
    testImplementation(deps.TestDeps.junit4)
    testImplementation(deps.TestDeps.mockk)
    testImplementation(deps.TestDeps.jupiterApi)
    testImplementation(deps.TestDeps.jupiterParams)
    testRuntimeOnly(deps.TestDeps.jupiterEngine)

    //Instrumentation Tests Libs
    androidTestImplementation(deps.InstrumentationTestDeps.androidxTestExt)

    //espresso
    implementation(deps.InstrumentationTestDeps.idlingRes)
    androidTestImplementation(deps.InstrumentationTestDeps.espressoCore)
    androidTestImplementation(deps.InstrumentationTestDeps.espressoContrib)


}