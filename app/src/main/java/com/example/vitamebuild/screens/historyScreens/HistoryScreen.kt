package com.example.vitamebuild.screens.historyScreens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.classes.Food
import com.example.vitamebuild.generalFunctions.saveToJsonFoodData
import com.example.vitamebuild.graphicalInterfaces.MyScaffold
import com.example.vitamebuild.screens.waterInputScreens.MyStyleColumn
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

@Composable
fun HistoryScreen(navController: NavHostController) {
    MyScaffold(
        navController,
        currentScreenName = R.string.meal_history,
        previousScreenRoute = "MAIN_SCREEN") {
        MyStyleColumn(textContent = R.string.history_of_eaten_meals) {


            SynchronizeButton()



            LastTimeSinceRecordedMealButton()

            ObjectHolder.globalMealHistoryList.forEachIndexed { index, food ->
                MealHistorySegment(food = food, index = index, navController) {}
                
            }
            Spacer(modifier = Modifier.padding(50.dp))

        }
        
    }

}

@Composable
fun SynchronizeButton() {
    val scope = rememberCoroutineScope()

    Button(onClick = {
        scope.launch {
            val httpClient = HttpClient(Android) {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(Json {
                        ignoreUnknownKeys = true
                    })
                }
            }
            var gson = Gson()
            val jsonFoodList: String = gson.toJson(ObjectHolder.globalMealHistoryList)
            Log.i("Test_Response", "${jsonFoodList}")

            try {
                val postSavedData =
                    httpClient.post<String>("http://192.168.1.7:5000/create-food-data") {
                        headers {
                            append(io.ktor.http.HttpHeaders.ContentType, "application/json")
                        }
                        body = jsonFoodList
                    }
                Log.i("Test_Response", "post successful, posted data: ${postSavedData}")

            } catch (e: Exception) {
                Log.i("Test_Response", "Unsuccessful post: ${e.message}")
            }

        }

    },
        enabled = ObjectHolder.globalUser.authorized) {
        Text(text = stringResource(id = R.string.synchronize_data))
    }
}

@Composable
fun LastTimeSinceRecordedMealButton() {
    val scope = rememberCoroutineScope()
    var lastRecordedMeal by remember { mutableStateOf("Last time since recorded meal") }

    Button(onClick = {
        scope.launch {
            val httpClient = HttpClient(Android) {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(Json {
                        ignoreUnknownKeys = true
                    })
                }
            }
            try {
                if (ObjectHolder.globalMealHistoryList.isNotEmpty()) {
                    val timeDifference =
                        httpClient.get<String> { url(
                            " http://192.168.1.4:5000/get-time-difference/" +
                                    "${ObjectHolder.globalMealHistoryList.first().foodDateEaten}/" +
                                    "${ObjectHolder.globalMealHistoryList.first().foodTimeEaten}"
                        ) }
                    lastRecordedMeal =
                        (timeDifference.toInt()/60).toString() +
                                " hours and " +
                                (timeDifference.toInt()%60).toString() +
                                " minutes since last recorded meal"
                    Log.i("Test_Response", "Success: ${timeDifference}")
                } else {
                    lastRecordedMeal = "No recorded meals!"
                }
            } catch (e: Exception) {
                Log.i("Test_Response", "Exception ${e.message} ${ObjectHolder.globalMealHistoryList.last().foodDateEaten}")
            }

            finally {
                httpClient.close()
            }
        }

    },
        enabled = ObjectHolder.globalUser.authorized) {
        Text(text = lastRecordedMeal)
    }
}


@Composable
fun MealHistorySegment(
    food: Food,
    index: Int,
    navController: NavHostController,
    onEditClick: () -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        var (isButtonEnabled, setIsButtonEnabled) = remember { mutableStateOf(true) }
        var context = LocalContext.current
        val scope = rememberCoroutineScope()
        val mutex = Mutex()
        Text(
            text = food.foodDateEaten + " " + food.foodTimeEaten,
            fontWeight = FontWeight(18),
            fontSize = 18.sp
        )
        Text(text = "Food: ${food.foodName}")
        Text(text = "Satisfaction: ${food.foodTastiness}")
        Text(text = "Rating: ${food.rating}")
        Text(text = "Fullness: ${food.foodAmountFullness}")

        Button(onClick = {
            ObjectHolder.newMeal = ObjectHolder.globalMealHistoryList[index]
            ObjectHolder.globalIndex = index
            navController.navigate(route = "MEAL_EDIT_SCREEN")
        },
            enabled = isButtonEnabled) {
            Text(text = stringResource(id = R.string.edit))

        }

        var textDeleted by remember { mutableStateOf("") }
        val textStringDeleted = stringResource(id = R.string.deleted)

        Button(onClick = {

            ObjectHolder.threadPool.execute {

                scope.launch {
                    mutex.withLock {
                        //deletedText = mutableStateOf("It's deleted")
                        delay(2000L)
                        setIsButtonEnabled(false)
                        ObjectHolder.globalMealHistoryList.removeAt(index)
                        saveToJsonFoodData(context)
                        Log.i("Test_Deletion", "Meal deleted successfully")
                        textDeleted = textStringDeleted

                    }

                    Log.i("Test_Deletion", "Meal deleted successfuly")

                }

            }
            //navController.navigate("MEAL_HISTORY")





        },
            enabled = isButtonEnabled) {
            Text(text = stringResource(id = R.string.delete))


        }
        Text(text = textDeleted)

    }
}


