package com.example.dindinn_atish.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Modified by Atish Agrawal on 26-10-2020.
 * Email: atish.agrawal@medtrail.in
 * Phone: +91-9716987018
 */

class DateTimeUtils {

    fun getTimeFromDateString(dateString: String): String {

        val formatter = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.ENGLISH)
        formatter.timeZone = TimeZone.getTimeZone("GMT")
        val formattedDate: Date = formatter.parse(dateString)!!

        val df = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
        df.timeZone = TimeZone.getTimeZone("GMT")
        val time = df.format(formattedDate)
        return time.toString()
    }


    fun getInitialTimeDifference(startTime: String, endTime: String): String {

        val formatter = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.ENGLISH)
        formatter.timeZone = TimeZone.getTimeZone("GMT")
        val start: Date = formatter.parse(startTime)!!
        val end: Date = formatter.parse(endTime)!!

        val diff: Long = end.time - start.time

        val seconds: Long = (TimeUnit.MILLISECONDS
            .toSeconds(diff)
                % 60)

        val minutes: Long = (TimeUnit.MILLISECONDS
            .toMinutes(diff)
                % 60)

        val hours: Long = (TimeUnit.MILLISECONDS
            .toHours(diff)
                % 24)


        var formattedRemainingTime = ""

        if (hours != 0L) {
            formattedRemainingTime += "$hours h "
        }
        if (minutes != 0L) {
            formattedRemainingTime += "$minutes m "
        }
        if (seconds != 0L) {
            formattedRemainingTime += "$seconds s "
        }


        return formattedRemainingTime.trim()

    }

    fun getFormattedTime(passedTime: Long): String {
        val seconds: Long = (TimeUnit.MILLISECONDS
            .toSeconds(passedTime)
                % 60)

        val minutes: Long = (TimeUnit.MILLISECONDS
            .toMinutes(passedTime)
                % 60)

        val hours: Long = (TimeUnit.MILLISECONDS
            .toHours(passedTime)
                % 24)


        var formattedRemainingTime = ""

        if (hours != 0L) {
            formattedRemainingTime += "$hours h "
        }
        if (minutes != 0L) {
            formattedRemainingTime += "$minutes m "
        }
        if (seconds != 0L) {
            formattedRemainingTime += "$seconds s "
        }


        return formattedRemainingTime.trim()

    }


    fun isTimeExpired(startTime: String, endTime: String): Boolean {

        val formatter = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.ENGLISH)
        formatter.timeZone = TimeZone.getTimeZone("GMT")
        val start: Date = formatter.parse(startTime)!!
        val end: Date = formatter.parse(endTime)!!

        val difference: Long = end.time - start.time

        return difference <= 0


    }

    fun getTimeDifference(startTime: String, endTime: String): Long {

        val formatter = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.ENGLISH)
        formatter.timeZone = TimeZone.getTimeZone("GMT")
        val start: Date = formatter.parse(startTime)!!
        val end: Date = formatter.parse(endTime)!!

        return end.time - start.time


    }

}