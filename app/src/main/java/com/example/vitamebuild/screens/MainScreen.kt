package com.example.navigationcomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.vitamebuild.R
import com.example.vitamebuild.graphicalInterfaces.MyScaffold
import com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons.GoToLogMealScreenButton
import com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons.GoToLogWaterIntakeButton
import com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons.GoToMealHistoryButton
import com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons.GoToSettingsScreenButton

@Composable
fun MainScreen(navController: NavHostController) {
    MyScaffold(navController, R.string.home, "") {
        MainScreenComposable(navController)

    }
}

@Composable
fun MainScreenComposable(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        GoToLogWaterIntakeButton(navController)
        GoToLogMealScreenButton(navController)
        GoToMealHistoryButton(navController)
        GoToSettingsScreenButton(navController)
    }
}

