package com.example.vitamebuild.screens.settingsScreens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.graphicalInterfaces.MyScaffold
import com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons.MyButton
import com.example.vitamebuild.screens.waterInputScreens.InputScreenText
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
                ObjectHolder.settings.saveSettingsToIniFile(context = context, "fontSizeButtons", ObjectHolder.settings.fontSizeButtons.toString())
            }
            MyButton(textID = R.string.medium) {
                ObjectHolder.settings.fontSizeButtons = 20
                navController.navigate( route = "APP_SETTINGS_SCREEN")
                ObjectHolder.settings.saveSettingsToIniFile(context = context, "fontSizeButtons", ObjectHolder.settings.fontSizeButtons.toString())
            }
            MyButton(textID = R.string.large) {
                ObjectHolder.settings.fontSizeButtons = 26
                navController.navigate( route = "APP_SETTINGS_SCREEN")
                ObjectHolder.settings.saveSettingsToIniFile(context = context, "fontSizeButtons", ObjectHolder.settings.fontSizeButtons.toString())
            }
            InputScreenText(textContent = R.string.fontStyle)
            MyButton(textID = R.string.defaultFont) {
                ObjectHolder.settings.fontStyleButtons = 1
                navController.navigate( route = "APP_SETTINGS_SCREEN")
                ObjectHolder.settings.saveSettingsToIniFile(context = context, "fontStyleButtons", ObjectHolder.settings.fontStyleButtons.toString())

            }
            MyButton(textID = R.string.cursive) {
                ObjectHolder.settings.fontStyleButtons = 2
                navController.navigate( route = "APP_SETTINGS_SCREEN")
                ObjectHolder.settings.saveSettingsToIniFile(context = context, "fontStyleButtons", ObjectHolder.settings.fontStyleButtons.toString())

            }
            MyButton(textID = R.string.serif) {
                ObjectHolder.settings.fontStyleButtons = 3
                navController.navigate( route = "APP_SETTINGS_SCREEN")
                ObjectHolder.settings.saveSettingsToIniFile(context = context, "fontStyleButtons", ObjectHolder.settings.fontStyleButtons.toString())

            }
            InputScreenText(textContent = R.string.button_style)
            MyButton(textID = R.string.button_style_1) {
                ObjectHolder.settings.buttonStyle = 1
                navController.navigate( route = "APP_SETTINGS_SCREEN")
                ObjectHolder.settings.saveSettingsToIniFile(context = context, "fontStyleButtons", ObjectHolder.settings.fontStyleButtons.toString())

            }
            MyButton(textID = R.string.button_style_2) {
                ObjectHolder.settings.buttonStyle = 2
                navController.navigate( route = "APP_SETTINGS_SCREEN")
                ObjectHolder.settings.saveSettingsToIniFile(context = context, "buttonStyle", ObjectHolder.settings.buttonStyle.toString())

            }

            Spacer(modifier = Modifier.height(200.dp))
        }


    }

}