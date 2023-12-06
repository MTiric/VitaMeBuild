package com.example.navigationcomponent

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavHostController
import com.example.vitamebuild.classes.Food
import com.example.vitamebuild.R

@Composable
fun MainScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = stringResource(id = R.string.my_new_meal), fontSize =30.sp)
        Button(
            onClick = {
                navController.navigate(
                    route = "FOOD_INPUT_SCREEN"
                )
            }
        ) {
            Text(text = stringResource(id = R.string.log_meal))
        }
        Button(
            onClick = {
                navController.navigate(
                    route = "MEAL_HISTORY"
                )
            }
        ) {
            Text(text = stringResource(id = R.string.meal_history))
        }
        Button(
            onClick = {
                navController.navigate(
                    route = "SETTINGS_SCREEN"
                )
            }
        ) {
            Text(text = stringResource(id = R.string.settings))
        }
    }
}