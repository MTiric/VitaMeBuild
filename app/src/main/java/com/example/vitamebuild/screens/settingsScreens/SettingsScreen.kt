package com.example.vitamebuild.screens.settingsScreens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.graphicalInterfaces.MyScaffold
import com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons.MyButton
import com.example.vitamebuild.screens.waterInputScreens.MyStyleColumn
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.Properties


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController) {
    MyScaffold(
        navController,
        currentScreenName = R.string.settings,
        previousScreenRoute = "MAIN_SCREEN"
    ) {
        MyStyleColumn(textContent = R.string.settings) {
            MyButton(textID = R.string.application_settings) {
                navController.navigate( route = "APP_SETTINGS_SCREEN")
            }
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
            val context = LocalContext.current
            var loadedValue by remember { mutableStateOf("") }

            SaveToIniButton(context = context)
            LoadFromIniButton(context = context, ) {loadedValue = it}
            if(loadedValue.isNotBlank()) {
                DisplayLoadedValue(value = loadedValue)
            }
        }



        }

}

@Composable
fun DisplayLoadedValue(value: String) {
    // This composable function displays the loaded value as a button text content
    Button(
        onClick = { /* Do something with the loaded value if needed */ }
    ) {
        Text(text = value)
    }
}
fun savePropertyToIniFile (
    context: android.content.Context,
    propertyName: String,
    propertyUnit: String) {
        val fileName = "settings.ini"
        val properties = Properties()
        properties.setProperty(propertyName, propertyUnit)

        try {
            val file = File(context.filesDir, fileName)
            FileWriter(file).use { writer ->
                properties.store(writer, "Settings INI File")
            }
            Log.i("SaveSettings", "saved data test: Storage successful")

        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("SaveSettings", "error: ${e.message}")

        }

}
@Composable
fun SaveToIniButton(context: android.content.Context) {
    Button(
        onClick = {
            val fileName = "settings.ini"
            val properties = Properties()
            properties.setProperty("measuringunits", ObjectHolder.settings.measuringunits)
            properties.setProperty("pushNotification", ObjectHolder.settings.pushNotification.toString())
            properties.setProperty("fontSizeButtons", ObjectHolder.settings.fontSizeButtons.toString())

            try {
                val file = File(context.filesDir, fileName)
                FileWriter(file).use { writer ->
                    properties.store(writer, "Settings INI File")
                }
                Log.i("SaveSettings", "saved data test: Storage successful")

            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("SaveSettings", "Storage not successful, error: ${e.message}")
            }

    }) {
        Text(text = stringResource(id = R.string.save_settings))

    }
}

@Composable
fun LoadFromIniButton(context: android.content.Context, onClickAction: (String) -> Unit) {
    Button(
        onClick = {
            val fileName = "settings.ini"
            val properties = Properties()

            try {
                val file = File(context.filesDir, fileName)
                FileReader(file).use { reader ->
                    properties.load(reader)
                }
                val valueForKey1 = properties.getProperty("fontSizeButtons") ?: ""
                onClickAction(valueForKey1)
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle the exception, perhaps show an error message
            }
        }
    ) {
        Text(text = "Load from INI File")
    }
}
