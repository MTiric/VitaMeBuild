package com.example.vitamebuild.screens.personalDataScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.vitamebuild.R
import com.example.vitamebuild.graphicalInterfaces.MyScaffold

@Composable
fun PersonalDataScreen(navController: NavHostController) {
    MyScaffold(navController,
        currentScreenName = R.string.personal_data,
        previousScreenRoute = "MAIN_SCREEN") {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ){
            Button(onClick = { navController.navigate(route = "SIGN_IN_SCREEN") }) {
             Text(text = "Sign in")
            }
            Button(onClick = { navController.navigate(route = "REGISTER_SCREEN") }) {
                Text(text = "Register")
            }

        }
        
    }

}

