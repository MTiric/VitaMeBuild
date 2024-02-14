package com.example.vitamebuild.classes

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.Properties

class Settings (var loggedIn: Boolean){
    var measuringunits: String = "Metric"
    var pushNotification: Boolean = true
    var user: Person = Person("defaultUser")
    var fontSizeButtons: Int = 16
    var fontStyleButtons: Int = 1 // 1 is default, 2 is cursive, 3 is serif
    var buttonStyle: Int = 1 // 1 is default, 2 is Tonal
    var fontSizeText: Int = 16


    fun saveSettingsToIniFile(
        context: android.content.Context,
        propertyName: String,
        propertyUnit: String
    ) {
        val fileName = "settings.ini"
        val properties = Properties()

        try {
            val file = File(context.filesDir, fileName)
            if (file.exists()) {
                FileReader(file).use { reader ->
                    properties.load(reader)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("SaveSettings", "error loading properties: ${e.message}")
        }

        properties.setProperty(propertyName, propertyUnit)

        try {
            val file = File(context.filesDir, fileName)
            FileWriter(file).use { writer ->
                properties.store(writer, "Settings INI File")
            }
            Log.i("SaveSettings", "saved data test: Storage successful")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("SaveSettings", "error saving properties: ${e.message}")
        }
    }


    fun loadFromIniFile(context: android.content.Context): Settings {
        val fileName = "settings.ini"
        val properties = Properties()

        try {
            val file = File(context.filesDir, fileName)
            FileReader(file).use { reader ->
                properties.load(reader)
            }
            val fontSizeButtonsString = properties.getProperty("fontSizeButtons") ?: ""
            this.fontSizeButtons = fontSizeButtonsString.toIntOrNull() ?: 0
            val fontStyleButtons = properties.getProperty("fontStyleButtons") ?: ""
            this.fontStyleButtons = fontStyleButtons.toIntOrNull() ?: 0
            val buttonStyle = properties.getProperty("buttonStyle") ?: ""
            this.buttonStyle = buttonStyle.toIntOrNull() ?: 0

        } catch (e: Exception) {
            e.printStackTrace()
            // Handle the exception, perhaps show an error message
        }

        return this
    }



}