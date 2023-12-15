package com.example.vitamebuild.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.vitamebuild.R
import com.example.vitamebuild.graphicalInterfaces.MyScaffold

@Composable
fun HealthInputScreen(navController: NavHostController) {
    MyScaffold(navController,
        currentScreenName = R.string.log_personal_health,
        previousScreenRoute = "MAIN_SCREEN") {

    }

}