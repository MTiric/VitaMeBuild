package com.example.vitamebuild.screens.historyScreens

import android.annotation.SuppressLint
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
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavHostController
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.classes.AndroidDownloader
import com.example.vitamebuild.classes.Food
import com.example.vitamebuild.generalFunctions.saveToJsonFoodData
import com.example.vitamebuild.graphicalInterfaces.MyScaffold
import com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons.MyButton
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.Json

@SuppressLint("MissingPermission")
@Composable
fun HistoryScreen(navController: NavHostController) {
    val CHANNEL_ID = "channelID"
    val NOTIFICATION_ID = 0
    var lastRecordedMeal by remember { mutableStateOf("Last time since recorded meal") }
    var lastRecordedMealChecked = false



    MyScaffold(
        navController,
        currentScreenName = R.string.meal_history,
        previousScreenRoute = "MAIN_SCREEN") {
        MyStyleColumn(textContent = R.string.history_of_eaten_meals) {
            val scope = rememberCoroutineScope()
            var synchronisationText: String = stringResource(id = R.string.synchronize_data)
            var synchronizedText:String = stringResource(id = R.string.synchronized_data)
            var buttonTextSynchronize by remember { mutableStateOf(synchronisationText) }
            var context = LocalContext.current

            Button(onClick = {
                ObjectHolder.threadPool.execute {
                    GlobalScope.launch {
                        delay(1000)
                        buttonTextSynchronize = synchronizeOnWebService(synchronizedText)
                    }
                }

            },
                enabled = ObjectHolder.globalUser.authorized) {
                Text(text = buttonTextSynchronize)
            }

            Button(onClick = {
                ObjectHolder.threadPool.execute {
                    scope.launch {
                        lastRecordedMeal = timeSinceLastMeal()

                        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                            .setContentTitle("When did you last eat?")
                            .setContentText(lastRecordedMeal)
                            .setSmallIcon(androidx.core.R.drawable.notification_bg)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .build()

                        val notificationManager = NotificationManagerCompat.from(context)

                        notificationManager.notify(NOTIFICATION_ID, notification)

                    }
                }

            },
                enabled = true) {
                Text(text = lastRecordedMeal)
            }

            val downloader = AndroidDownloader(LocalContext.current)

            MyButton(textID = R.string.download_recipes) {
                ObjectHolder.threadPool.execute {
                    scope.launch {
                        downloader.downloadFile("https://www.family-action.org.uk/content/uploads/2019/07/meals-more-recipes.pdf")

                    }
                }


            }

            MyButton(textID = R.string.do_all_above) {
                if (ObjectHolder.globalUser.authorized) {
                    ObjectHolder.threadPool.execute {
                        GlobalScope.launch {
                            delay(1000)
                            buttonTextSynchronize = synchronizeOnWebService(synchronizedText)
                        }
                    }
                }
                ObjectHolder.threadPool.execute {
                    scope.launch {
                        lastRecordedMeal = timeSinceLastMeal()
                    }
                }
                ObjectHolder.threadPool.execute {
                    scope.launch {
                        downloader.downloadFile("https://www.family-action.org.uk/content/uploads/2019/07/meals-more-recipes.pdf")

                    }
                }

            }

            ObjectHolder.globalMealHistoryList.forEachIndexed { index, food ->
                MealHistorySegment(food = food, index = index, navController) {}
                
            }
            Spacer(modifier = Modifier.padding(50.dp))

        }
        
    }

}


@Composable
fun DownloadRecipesButton() {

}

@Composable
fun SynchronizeButton() {

}

suspend fun synchronizeOnWebService(synchronizedText: String): String {

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
            Log.i("Test_Response", "${ObjectHolder.globalUser.heightInches}")
            Log.i("Test_Response", "${ObjectHolder.globalUser.height}")


            try {
                val postSavedData =
                    httpClient.post<String>(
                        "${ObjectHolder.globalURLRESTAPI}/create-food-data/" +
                                "${ObjectHolder.globalUser.uniqueToken}/" +
                                "${ObjectHolder.globalUser.userMailAddress}") {
                        headers {
                            append(io.ktor.http.HttpHeaders.ContentType, "application/json")
                        }
                        body = jsonFoodList
                    }
                Log.i("Test_Response", "post successful, posted data: $postSavedData")



            } catch (e: Exception) {
                Log.i("Test_Response", "Unsuccessful post: ${e.message}")
            }


    return synchronizedText
}

@Composable
fun LastTimeSinceRecordedMealButton() {

}

suspend fun timeSinceLastMeal(): String {

    var lastMeal: String = ""

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
                            " http://192.168.1.9:5000/get-time-difference/" +
                                    "${ObjectHolder.globalMealHistoryList.first().foodDateEaten}/" +
                                    "${ObjectHolder.globalMealHistoryList.first().foodTimeEaten}"
                        ) }
                    var lastRecordedMeal = (timeDifference.toInt()/60).toString() +
                            " hours and " +
                            (timeDifference.toInt()%60).toString() +
                            " minutes since last recorded meal"
                    Log.i("Test_Response", "Success: ${timeDifference} $lastRecordedMeal")
                    lastMeal = lastRecordedMeal
                } else {
                    var lastRecordedMeal = "No recorded meals!"
                    lastMeal = lastRecordedMeal
                }
            } catch (e: Exception) {
                var lastRecordedMeal = "Something went wrong"
                lastMeal = lastRecordedMeal
                Log.i("Test_Response", "Exception ${e.message} ${ObjectHolder.globalMealHistoryList.last().foodDateEaten}")
            }

            finally {
                httpClient.close()
            }

    Log.i("Test_Response", "$lastMeal")
    return lastMeal
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
                        setIsButtonEnabled(false)
                        delay(2000L)
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


