package com.example.vitamebuild

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.vitamebuild.generalFunctions.getFoodList
import com.example.vitamebuild.graphicalInterfaces.navigation.CustomNavHost
import com.example.vitamebuild.ui.theme.VitaMeBuildTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

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
                    //CustomNavHost(navController = navController)
                    //Text(text = ObjectHolder.foodApiSearch.foods[0].description)
                    LoadingIndicator()

                }
            }
        }


    }

}

@Composable
fun LoadingIndicator() {
    var currentProgress by remember { mutableStateOf(0f)   }
    var loading by remember { mutableStateOf(false)  }
    var foodDescription by remember { mutableStateOf(ObjectHolder.newMeal.foodContent.description) }
    val scope = rememberCoroutineScope()

    Column (
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = {
            loading = true
            var hasSucceded = false
            var numberOfTriesRemaining = 3
            scope.launch {
                getFoodList("peanuts")
                loadProgress { progress ->
                    currentProgress = progress
                }
                for (i in 0..2) {
                    try {
                        foodDescription = ObjectHolder.foodApiSearch.foods[0].description
                        Log.i("Test_Response", "onResponsefromButton: ${ObjectHolder.foodApiSearch.foods[0].description}")
                        loading = false
                    } catch (e: Exception) {
                        Log.i("Test_Response", "onResponse: api call fail #${i}")
                        delay(1000)
                    }
                }

            }
        }, enabled = !loading) {
            Text(text = foodDescription)
        }

        if (loading) {
            LinearProgressIndicator(
                progress =  currentProgress ,
                modifier = Modifier.fillMaxWidth(),
            )
        }

    }
}

suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(10)
    }
}

@Composable
fun ListItem() {
    var foodDescription by remember { mutableStateOf(ObjectHolder.newMeal.foodContent.description) }

    Column {
        Button(onClick = {
            getFoodList("egg")
            foodDescription = ObjectHolder.foodApiSearch.foods[0].description
        }) {
            Text(text = "Press this button")
        }

        Text(text = foodDescription)
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