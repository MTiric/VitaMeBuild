package com.example.vitamebuild.screens.settingsScreens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.graphicalInterfaces.MyScaffold
import com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons.MyButton
import com.example.vitamebuild.screens.waterInputScreens.MyStyleColumn

@Composable
fun AppSettingsScreen(navController: NavHostController) {
    MyScaffold(
        navController,
        currentScreenName = R.string.app_settings,
        previousScreenRoute = "SETTINGS_SCREEN"
    ) {
        MyStyleColumn(textContent = R.string.set_button_font_size) {
            var context = LocalContext.current
            MyButton(textID = R.string.small) {
                ObjectHolder.settings.fontSizeButtons = 16
                navController.navigate( route = "APP_SETTINGS_SCREEN")
                SavePropertyToIniFile(context = context, "fontSizeButtons", ObjectHolder.settings.fontSizeButtons.toString())
            }
            MyButton(textID = R.string.medium) {
                ObjectHolder.settings.fontSizeButtons = 20
                navController.navigate( route = "APP_SETTINGS_SCREEN")
                SavePropertyToIniFile(context = context, "fontSizeButtons", ObjectHolder.settings.fontSizeButtons.toString())
            }
            MyButton(textID = R.string.large) {
                ObjectHolder.settings.fontSizeButtons = 26
                navController.navigate( route = "APP_SETTINGS_SCREEN")
                SavePropertyToIniFile(context = context, "fontSizeButtons", ObjectHolder.settings.fontSizeButtons.toString())
            }
        }


    }

}