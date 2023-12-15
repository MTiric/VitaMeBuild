package com.example.vitamebuild.graphicalInterfaces

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyScaffold(navController: NavHostController,
               currentScreenName: Int,
               previousScreenRoute: String,
               content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBarCustom(navController, curScreen = currentScreenName, previousScreenRoute)
        },
        bottomBar = {
            BottomAppBarCustom(navController)
        }
    ) {
        content()

    }
}