package com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.vitamebuild.R

@Composable
fun GoToLogWaterIntakeButton(
    navController: NavHostController) {

    MyButton(
        textID = R.string.log_water_intake
    ) {
        navController.navigate( route = "WATER_INPUT_SCREEN" )

    }
}

@Composable
fun GoToButton(
    navController: NavHostController,
    route: String,
    buttonText: String
    ) {
    Button(
        onClick = {
            navController.navigate(
                route = route
            )
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = buttonText)
    }

}
