package com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.vitamebuild.R

@Composable
fun GoToLogPhysicalActivityButton(navController: NavHostController) {
    Button(
        onClick = {
            navController.navigate(
                route = "PHYSICAL_ACTIVITY_SCREEN"
            )
        }
    ) {
        Text(text = stringResource(id = R.string.log_physical_activity))
    }

}