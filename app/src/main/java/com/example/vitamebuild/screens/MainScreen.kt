package com.example.navigationcomponent

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.graphicalInterfaces.MyScaffold
import com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons.GoToLogBowelMovementsButton
import com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons.GoToLogMealScreenButton
import com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons.GoToLogPersonalHealth
import com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons.GoToLogPhysicalActivityButton
import com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons.GoToLogPrayerMeditaion
import com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons.GoToLogSleepButton
import com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons.GoToLogWaterIntakeButton

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
        GoToLogMealScreenButton(navController)
        GoToLogWaterIntakeButton(navController)
        GoToLogSleepButton(navController)
        GoToLogBowelMovementsButton(navController)
        GoToLogPhysicalActivityButton(navController)
        GoToLogPersonalHealth(navController)
        GoToLogPrayerMeditaion(navController)

        //AndroidContextComposeDemo()
        Button(onClick = {
            ObjectHolder.settings.fontSizeButtons = 16
            navController.navigate( route = "MAIN_SCREEN")
        }) {

        }

        val context = LocalContext.current
        Button(onClick = {

            val packageManager: PackageManager = context.packageManager
            val intent: Intent = packageManager.getLaunchIntentForPackage(context.packageName)!!
            val componentName: ComponentName = intent.component!!
            val restartIntent: Intent = Intent.makeRestartActivityTask(componentName)
            context.startActivity(restartIntent)
            Runtime.getRuntime().exit(0)
        }) {
            Text(text = "Restart")
            
        }


    }
}

@Composable
fun AndroidContextComposeDemo() {
    val context = LocalContext.current
    Text(text = "Read this string from Context: $context")
}


