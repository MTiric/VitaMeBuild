package com.example.vitamebuild

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.vitamebuild.classes.Food
import com.example.vitamebuild.graphicalInterfaces.CustomNavHost
import com.example.vitamebuild.ui.theme.VitaMeBuildTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            VitaMeBuildTheme {
                val backgroundColor = MaterialTheme.colorScheme.background
                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = backgroundColor
                ) {
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