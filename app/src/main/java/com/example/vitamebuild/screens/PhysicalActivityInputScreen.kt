package com.example.vitamebuild.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.vitamebuild.R
import com.example.vitamebuild.graphicalInterfaces.MyScaffold

@Composable
fun PhysicalActivityInputScreen(navController: NavHostController) {
    MyScaffold(navController,
        currentScreenName = R.string.log_physical_activity,
        previousScreenRoute = "MAIN_SCREEN") {

    }

}