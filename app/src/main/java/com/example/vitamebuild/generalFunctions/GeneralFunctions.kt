package com.example.vitamebuild.generalFunctions

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getCurrentHourAsInt(): Int{
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.HOUR_OF_DAY)
}

fun getCurrentMinuteAsInt(): Int{
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.MINUTE)
}

fun getCurrentTimeAsString(): String {
    var currentHourString = ""
    var currentMinuteString = ""

    if(getCurrentHourAsInt()<10){
        currentHourString = "0" + getCurrentHourAsInt().toString()
    } else {
        currentHourString = getCurrentHourAsInt().toString()
    }

    if(getCurrentMinuteAsInt()<10){
        currentMinuteString = "0" + getCurrentMinuteAsInt().toString()
    } else {
        currentMinuteString = getCurrentMinuteAsInt().toString()
    }

    return "$currentHourString:$currentMinuteString"
}

fun getCurrentDateAsString(): String {
    val currentDate = LocalDate.now() // Get the current time
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // Define the format

    return currentDate.format(formatter) // Convert time to string using the defined format
}









