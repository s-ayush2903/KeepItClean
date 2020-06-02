package com.stvayush.keepitclean.business.domain.utils

import androidx.test.espresso.idling.CountingIdlingResource


object EspressoIdlingRes {

    private val CLASS_NAME = "EspressoIdlingRes"

    private const val RESOURCE = "GLOBAL"

    @JvmField
    val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        printLogD(CLASS_NAME, "INCREMENTING.")
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            printLogD(CLASS_NAME, "DECREMENTING.")
            countingIdlingResource.decrement()
        }
    }

    fun clear() {
        if (!countingIdlingResource.isIdleNow) {
            decrement()
            clear()
        }
    }
}
