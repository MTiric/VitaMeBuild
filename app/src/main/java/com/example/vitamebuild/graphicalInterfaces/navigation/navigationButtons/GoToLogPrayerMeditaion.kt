package com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.vitamebuild.R

@Composable
fun GoToLogPrayerMeditaion(navController: NavHostController) {
    MyButton(textID = R.string.log_prayer_meditation) {
        navController.navigate(
            route = "PRAYER_MEDITATION_SCREEN"
        )
    }

}