package deps

/**
 * Contain deps which assist in instrumentation tests only
 * */

object InstrumentationTestDeps{

  //Kotlin Dep for testing
  const val ktTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
  const val coRoutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"

  //Espresso Deps
  const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
  const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espressoCore}"
  const val idlingRes = "androidx.test.espresso:espresso-idling-resource:${Versions.espressoIdlingRes}"

  //Normal Test Deps
  const val testRunner = "androidx.test:runner:${Versions.testRunner}"
  const val testRules = "androidx.test:rules:${Versions.testRunner}"
  const val testCoreKtx = "androidx.test:core-ktx:${Versions.testCore}"

  //Mockk
  const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockkVersion}"

  //UI Testing
  const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragmentVersion}"
  const val androidxTestExt = "androidx.test.ext:junit-ktx:${Versions.androidxTestExt}"
  const val navTesting = "androidx.navigation:navigation-testing:${Versions.navComponents}"

  //custom test runner
  const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"  //i'll use a custom test runner here later

}