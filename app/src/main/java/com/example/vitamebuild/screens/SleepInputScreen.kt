package com.example.vitamebuild.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.graphicalInterfaces.EditTextField
import com.example.vitamebuild.graphicalInterfaces.MyScaffold
import com.example.vitamebuild.screens.waterInputScreens.InputScreenText
import com.example.vitamebuild.screens.waterInputScreens.MyStyleColumn

@Composable
fun SleepInputScreen(navController: NavHostController) {
    MyScaffold(navController,
        currentScreenName = R.string.log_sleep,
        previousScreenRoute = "MAIN_SCREEN") {
        SleepInputDetailed(navController)

    }

}


@Composable
fun SleepInputDetailed(navController: NavHostController) {
    MyStyleColumn(textContent = R.string.last_night_I_went_to_sleep) {
        Spacer(modifier = Modifier.height(50.dp))

        Button(onClick = { /*TODO*/ }) {
            Text(text = "00:00")
            
        }

        InputScreenText(textContent = R.string.and_I_woke_up)

        Button(onClick = { /*TODO*/ }) {
            Text(text = "08:00")

        }

        InputScreenText(textContent = R.string.how_many_times_did_you_wake_up)

        var timesWoken by remember { mutableIntStateOf(0) }
        EditTextField(
            value = timesWoken.toString(),
            onValueChanged = { timesWoken = it.toIntOrNull() ?: 0 },
            label = R.string.times_woken_up,
            //leadingIcon = R.drawable.template,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            singleLineState = false,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
        )
        //assign value to object

        InputScreenText(textContent = R.string.how_many_times_bathroom)

        var timesBathroom by remember { mutableIntStateOf(0) }
        EditTextField(
            value = timesWoken.toString(),
            onValueChanged = { timesWoken = it.toIntOrNull() ?: 0 },
            label = R.string.bathroom_count,
            //leadingIcon = R.drawable.template,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            singleLineState = false,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
        )
        //assign value to object

        InputScreenText(textContent = R.string.rate_sleep_quality)

    }

}
