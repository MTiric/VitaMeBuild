package com.example.vitamebuild.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vitamebuild.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.settings),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    navController.navigate(
                        route = "MAIN_SCREEN"
                    )
                }
            ) {
                Text(text = stringResource(id = R.string.units_of_measurement),
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth())
            }
            Button(
                onClick = {
                    navController.navigate(
                        route = "MAIN_SCREEN"
                    )
                }
            ) {
                Text(text = stringResource(id = R.string.notifications),
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth())
            }
            Button(
                onClick = {
                    navController.navigate(
                        route = "MAIN_SCREEN"
                    )
                }
            ) {
                Text(text = stringResource(id = R.string.download_personal_data),
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth())
            }
            Button(
                onClick = {
                    navController.navigate(
                        route = "MAIN_SCREEN"
                    )
                }
            ) {
                Text(text = stringResource(id = R.string.erase_personal_data),
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth())
            }

        }
    }
}
