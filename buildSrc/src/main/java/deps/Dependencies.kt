package deps

/**
 *  Contains only regular deps that are used in general functioning of the module
 *  i.e., no test deps
 */

object Dependencies {

  //Kotlin Deps
  const val ktStandardLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
  const val ktReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinVersion}"
  const val ktx = "androidx.core:core-ktx:${Versions.ktx}"

  //For Network Calls using Kt
  const val ktCoRoutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
  const val ktCoRoutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
  const val ktCoRoutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutinesPlayServices}"

  //dagger
  const val dagger = "com.google.dagger:dagger:${Versions.dagger}"

  //Jetpack Deps
  const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navComponents}"
  const val navRuntime = "androidx.navigation:navigation-runtime:${Versions.navComponents}"
  const val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.navComponents}"
  const val navDynamic = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navComponents}"

  //Lifecycle handling
  const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycleVersion}"
  const val lifecycleCoRoutines = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"

  //Material Design Deps
  const val materialDialogs = "com.afollestad.material-dialogs:core:${Versions.materialDialogs}"
  const val materialDialogsInput = "com.afollestad.material-dialogs:input:${Versions.materialDialogs}"

  //Room Deps
  const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
  const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
  const val playCore = "com.google.android.play:core:${Versions.playCore}"

  //for Memory Leaks
  const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

  //FireBase Deps
  const val firebaseFirestore = "com.google.firebase:firebase-firestore-ktx:${Versions.firebaseFirestore}"
  const val firebaseAuth = "com.google.firebase:firebase-auth:${Versions.firebaseAuth}"
  const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx:${Versions.firebaseAnalytics}"

  //Retrofit & gson Deps
  const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2Version}"
  const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2Version}"

  //some random dep
  const val markdownProcessor = "com.yydcdut:markdown-processor:${Versions.markdownProcessor}"

}