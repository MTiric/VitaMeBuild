package com.example.vitamebuild.graphicalInterfaces

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.vitamebuild.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyScaffold(navController: NavHostController,
               currentScreen: Int,
               previousScren: String,
               content: @Composable () -> Unit) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBarCustom(navController, curScreen = currentScreen, previousScren)
        },
        bottomBar = {
            BottomAppBarCustom(navController)
        }
    ) {
        content()

    }
}