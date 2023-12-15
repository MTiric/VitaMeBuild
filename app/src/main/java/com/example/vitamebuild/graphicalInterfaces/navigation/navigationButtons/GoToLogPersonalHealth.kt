package com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.vitamebuild.R

@Composable
fun GoToLogPersonalHealth(navController: NavHostController) {
    MyButton(textID = R.string.log_personal_health) {
        navController.navigate(
            route = "HEALTH_INPUT_SCREEN"
        )
        
    }

}