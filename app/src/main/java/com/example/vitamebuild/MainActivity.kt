package com.example.vitamebuild

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.vitamebuild.classes.Settings
import com.example.vitamebuild.graphicalInterfaces.navigation.CustomNavHost
import com.example.vitamebuild.ui.theme.VitaMeBuildTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            VitaMeBuildTheme {

                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    ObjectHolder.settings  = ObjectHolder.settings.loadFromIniFile(context)
                    val navController = rememberNavController()
                    CustomNavHost(navController = navController)
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VitaMeBuildTheme {
        val navController = rememberNavController()
        CustomNavHost(navController = navController)
    }
}