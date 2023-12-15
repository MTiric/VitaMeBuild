package com.example.vitamebuild.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.vitamebuild.R
import com.example.vitamebuild.graphicalInterfaces.MyScaffold

@Composable
fun BowelMovementsInputScreen(navController: NavHostController) {
    MyScaffold(navController,
        currentScreenName = R.string.log_bowel_movements,
        previousScreenRoute = "MAIN_SCREEN") {

    }

}