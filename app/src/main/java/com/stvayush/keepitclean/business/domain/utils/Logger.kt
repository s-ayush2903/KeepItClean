package com.stvayush.keepitclean.business.domain.utils

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.stvayush.keepitclean.business.domain.utils.Constants.DEBUG
import com.stvayush.keepitclean.business.domain.utils.Constants.TAG

/** A custom logging class */

var isUnitTest = false

fun printLogD(className: String?, message: String) {
    if (DEBUG && !isUnitTest) {
        Log.d(TAG, "$className: $message")
    } else if (DEBUG && isUnitTest) {
        println("$className: $message")
    }
}

/*
    Priorities: Log.DEBUG, Log. etc....
 */
fun cLog(msg: String?) {
    msg?.let {
        if (!DEBUG) {
            FirebaseCrashlytics.getInstance().log(it)
        }
    }

}