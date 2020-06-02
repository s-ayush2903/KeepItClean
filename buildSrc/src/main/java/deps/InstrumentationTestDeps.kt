package deps

/**
 * Contain only deps which assist in instrumentation tests
 * */

object InstrumentationTestDeps{

  const val ktTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
  const val coRoutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"
  const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
  const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espressoCore}"
  const val idlingRes = "androidx.test.espresso:espresso-idling-resource:${Versions.espressoIdlingRes}"
  const val testRunner = "androidx.test:runner:${Versions.testRunner}"
  const val testRules = "androidx.test:rules:${Versions.testRunner}"
  const val testCoreKtx = "androidx.test:core-ktx:${Versions.testCore}"
  const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockkVersion}"
  const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragmentVersion}"
  const val androidxTestExt = "androidx.test.ext:junit-ktx:${Versions.androidxTestExt}"
  const val navTesting = "androidx.navigation:navigation-testing:${Versions.navComponents}"

  const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"  //i'll use a custom test runner here later

}