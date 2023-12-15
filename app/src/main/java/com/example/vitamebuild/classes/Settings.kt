package com.example.vitamebuild.classes

import androidx.compose.ui.platform.LocalContext
import java.io.File
import java.io.FileReader
import java.util.Properties

class Settings (var loggedIn: Boolean){
    var measuringunits: String = "Metric"
    var pushNotification: Boolean = true
    var user: Person = Person("defaultUser")
    var fontSizeButtons: Int = 16
    var fontSizeText: Int = 16

    fun storeToIniFile() {

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

        } catch (e: Exception) {
            e.printStackTrace()
            // Handle the exception, perhaps show an error message
        }

        return this
    }



}