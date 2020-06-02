package com.stvayush.keepitclean.business.domain.utils

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DateUtils
@Inject
constructor(
    private val dateFormat: SimpleDateFormat
) {

    /** "2020-05-31 HH:mmm:ss" is the default date format in our local database for
     *   storing so to display 'em in the screen a few conversions are needed
     *   We'll need to perform few more conversions as we recieve the timestamps/dates from the firestore
     *   A total we'll need to perform 3 conversions namely:
     *
     * i) SQLite database-> how does the date look when it is inserted/sent/stored in the Room persistence library(SQLite)
     *                     * I'll be storing dates in SQLite db as Strings of format-> yyyy--mm--dd
     * ii) FireStore-> how does the date look when it is sent/inserted in the FireStore
     *                     * FireStore has its own built-in data type, Timestamp,
     *                     so we'll be removing the time part from the timestamp
     *                     retrieved from FireStore to display the date
     * iii) In the Views-> how the date is being displayed on the device screen (I really don't know why i'm categorizing 'em as 3 instead of 2)
     *                     * Cut-off time from fireStore timestamp and display on the views
     * */


//    fun removeTimeFromDate(sd:String): String{
//        return sd.substring(0,sd.indexOf(" "))
//    }

    /** As stated earlier, it is a method to remove time from the timestamps */
    fun String.cutOffTime(): String = this.substring(0, this.indexOf(" "))

//    fun convertFirebaseTimestampToStringDate(timestamp:Timestamp):String{
//        return dateFormat.format(timestamp.toDate())
//    }

    //    fun fireTime(timestamp: Timestamp)=dateFormat.format(timestamp.toDate())
    //convertFirebaseTimestampToStringDate
    fun Timestamp.fire(): String = dateFormat.format(this.toDate())
    //TODO::check if extension to string is redundant above

    /** Reverse conversion from normal date to firebase timestamp to store date there(in the server) */
    fun String.reverseFire(): Timestamp = Timestamp(dateFormat.parse(this)!!)

    /** For getting current timestamp */
    fun getTimestamp(): String = dateFormat.format(Date())

}

