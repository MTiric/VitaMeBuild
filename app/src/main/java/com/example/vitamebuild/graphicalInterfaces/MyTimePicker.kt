package com.example.vitamebuild.graphicalInterfaces

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import com.example.vitamebuild.generalFunctions.getCurrentHourAsInt
import com.example.vitamebuild.generalFunctions.getCurrentMinuteAsInt

object TimePickerTimeHolder{
    var timePickerTime = ""
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTimePickerMeal(){
    var timePickerHourString = ""
    var timePickerMinuteString = ""

    val timePickerState = rememberTimePickerState(
        initialHour = getCurrentHourAsInt(),
        initialMinute = getCurrentMinuteAsInt(),
        is24Hour = true
    )

    TimePicker(state = timePickerState)

    if(timePickerState.hour < 10) {
        timePickerHourString = "0${timePickerState.hour}"
    } else { timePickerHourString = "${timePickerState.hour}" }

    if(timePickerState.minute < 10) {
        timePickerMinuteString = "0${timePickerState.minute}"
    } else { timePickerMinuteString = "${timePickerState.minute}" }

    TimePickerTimeHolder.timePickerTime = "$timePickerHourString:$timePickerMinuteString"
    Text(text = "$timePickerHourString:$timePickerMinuteString")
}

