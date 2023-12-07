package com.example.vitamebuild.generalFunctions

import android.icu.util.Calendar

fun getCurrentHourAsInt(): Int{
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.HOUR_OF_DAY)
}

fun getCurrentMinuteAsInt(): Int{
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.MINUTE)
}






