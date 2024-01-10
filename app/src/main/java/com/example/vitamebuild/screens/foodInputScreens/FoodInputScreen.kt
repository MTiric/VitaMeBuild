package com.example.vitamebuild.screens.foodInputScreens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.generalFunctions.getFoodList
import com.example.vitamebuild.graphicalInterfaces.EditTextField
import com.example.vitamebuild.graphicalInterfaces.MyScaffold
import com.example.vitamebuild.loadProgress
import com.example.vitamebuild.screens.waterInputScreens.InputScreenText
import com.example.vitamebuild.screens.waterInputScreens.MyStyleColumn
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoodInputScreen(navController: NavHostController) {
    MyScaffold(navController,
        currentScreenName = R.string.meal_input_screen,
        previousScreenRoute = "MAIN_SCREEN") {
        FoodInputDetailed(navController)
    }
}

@Composable
fun FoodInputFetchedMeal(navController: NavHostController) {
    MyScaffold(
        navController,
        currentScreenName = R.string.food_internet_data,
        previousScreenRoute = "FOOD_INPUT_SCREEN") {
        var showDialog by remember { mutableStateOf(false) }

        MyStyleColumn(textContent = R.string.available_foods) {

            ObjectHolder.foodApiSearch.foods.forEachIndexed { index, food ->
                Button(onClick = {
                    Log.i("Test_Response", "onResponse: ${food.foodCategory}")
                    ObjectHolder.newMeal.foodContent.description = food.description
                    ObjectHolder.newMeal.foodContent.fdcId = food.fdcId
                    ObjectHolder.newMeal.foodContent.dataType = food.dataType
                    ObjectHolder.newMeal.foodContent.foodCategory = food.foodCategory
                    ObjectHolder.newMeal.foodContent.foodCategoryId = food.foodCategoryId
                    ObjectHolder.newMeal.foodContent.brandName = food.brandName
                    ObjectHolder.newMeal.foodContent.ingredients = food.ingredients

                    showDialog = true



                }) {
                    if (food.brandName != null) {
                        Text(
                            text = "${food.description} ${food.brandName}"
                        )
                    } else {
                        Text(text = food.description)
                    }
                }


            }
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                    },
                    title = {
                        Text(text = ObjectHolder.newMeal.foodContent.description)
                    },
                    text = {
                        ObjectHolder.newMeal.foodContent.ingredients?.let { Text(text = it) }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDialog = false
                                // Perform the actions upon confirmation
                            }
                        ) {
                            Text("Confirm")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                showDialog = false
                                // Perform actions if declined or dismissed
                            }
                        ) {
                            Text("Decline")
                        }
                    },
                )
            }


            Spacer(modifier = Modifier.height(50.dp))
        }

    }
}
@Composable
fun FoodInputDetailed(navController: NavHostController) {
    MyStyleColumn(textContent = R.string.today_I_ate) {
        Spacer(modifier = Modifier.height(100.dp))

        //Input for the name of the food
        FoodInputFoodName(navController)
        InputScreenText(textContent = R.string.at_around)
        //Input field for the time of the meal, by default it's now
        FoodInputTimeEaten(navController)
        //Input field for the date of the meal, by default it's today
        //FoodInputDateEaten() //for now unused
        //Input field for the place of the meal
        FoodInputPlaceEaten()
        //Input field for the amount of food in grams
        //FoodInputWeightInGrams() //for now unused, will decide later if we will use this
        //Input slider for how full the person was feeling with this meal
        FoodInputFullness()
        //Input slider for the tastiness of the food
        FoodInputTastiness()
        //Input slider for the rating of the food
        FoodInputRating()


        Spacer(modifier = Modifier.height(32.dp))
        SubmitButton(navController, "IS_THIS_YOUR_MEAL")

        Spacer(modifier = Modifier.height(200.dp))
    }
}

@Composable
fun SubmitButton(
    navController: NavHostController,
    route: String
) {
    Button(onClick = {
        navController.navigate(route)

    },
        modifier = Modifier
            .height(72.dp)
            .fillMaxWidth()) {
        Text(stringResource(R.string.submit_button))
    }
}

@Composable
fun FoodInputRating() {
    var rating by remember { mutableFloatStateOf(0f) }

    Text(text = stringResource(id = R.string.food_rating))
    Slider(
        value = rating,
        onValueChange = { rating = it },
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.secondary,
            activeTrackColor = MaterialTheme.colorScheme.secondary,
            inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        steps = 8,
        valueRange = 1f..10f
    )
    Text(text = rating.toInt().toString())

    ObjectHolder.newMeal.rating = rating
}

@Composable
fun FoodInputTastiness() {
    var tastiness by remember { mutableFloatStateOf(0f) }

    Text(text = stringResource(id = R.string.food_tastiness))
    Slider(
        value = tastiness,
        onValueChange = { tastiness = it },
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.secondary,
            activeTrackColor = MaterialTheme.colorScheme.secondary,
            inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        steps = 8,
        valueRange = 1f..10f
    )
    Text(text = tastiness.toInt().toString())

    ObjectHolder.newMeal.foodTastiness = tastiness
}

@Composable
fun FoodInputFullness() {
    var amountFullness by remember { mutableFloatStateOf(0f) }

    Text(text = stringResource(id = R.string.food_amount_fullness))
    Slider(
        value = amountFullness,
        onValueChange = { amountFullness = it },
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.secondary,
            activeTrackColor = MaterialTheme.colorScheme.secondary,
            inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        steps = 8,
        valueRange = 1f..10f
    )
    Text(text = amountFullness.toInt().toString())

    ObjectHolder.newMeal.foodAmountFullness = amountFullness
}

@Composable
fun FoodInputWeightInGrams() {
    var amountInGrams by remember { mutableStateOf("") }

    EditTextField(
        value = amountInGrams,
        onValueChanged = { amountInGrams = it },
        label = R.string.food_amount_in_grams,
        //leadingIcon = R.drawable.template,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        singleLineState = true,
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth(),
    )

    ObjectHolder.newMeal.amountInGrams = amountInGrams.toIntOrNull() ?: 0
}

@Composable
fun FoodInputPlaceEaten() {
    var place by remember { mutableStateOf("") }

    EditTextField(
        value = place,
        onValueChanged = { place = it },
        label = R.string.food_place,
        //leadingIcon = R.drawable.template,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        singleLineState = false,
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth(),
    )

    ObjectHolder.newMeal.foodPlace = place
}

@Composable
fun FoodInputDateEaten() {
    var dateEaten by remember { mutableStateOf(ObjectHolder.newMeal.foodDateEaten) }

    EditTextField(
        value = dateEaten,
        onValueChanged = { dateEaten = it },
        label = R.string.food_date_eaten,
        //leadingIcon = R.drawable.template,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        singleLineState = true,
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth(),
    )
    ObjectHolder.newMeal.foodDateEaten = dateEaten
}

@Composable
fun FoodInputFoodName(navController: NavHostController) {
    var foodName by remember { mutableStateOf(ObjectHolder.newMeal.foodName) }
    var currentProgress by remember { mutableStateOf(0f)   }
    var loading by remember { mutableStateOf(false)  }
    var foodDescription by remember { mutableStateOf(ObjectHolder.newMeal.foodContent.description) }
    val scope = rememberCoroutineScope()

    Row {
        EditTextField(
            value = foodName,
            onValueChanged = { foodName = it },
            label = R.string.food_name,
            //leadingIcon = R.drawable.template,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            singleLineState = false,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .weight(1f),

        )
        Button(onClick = {
            loading = true
            scope.launch {
                getFoodList(foodName)
                loadProgress { progress ->
                    currentProgress = progress
                }
                val timerInSecondsLimit = 20
                for (i in 0..timerInSecondsLimit) {
                    try {
                        foodDescription = ObjectHolder.foodApiSearch.foods[0].description
                        Log.i("Test_Response", "onResponsefromButton: ${ObjectHolder.foodApiSearch.foods[0].description}")
                        loading = false
                        navController.navigate(
                            route = "FOOD_INTERNET_DATA_SCREEN"
                        )
                        break
                    } catch (e: Exception) {
                        Log.i("Test_Response", "onResponse: api call fail #${i}")
                        delay(1000)
                        if (i == timerInSecondsLimit) loading = false
                    }
                }

            }

                         }, enabled = !loading,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.25f)
                .fillMaxHeight(

                )) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search food",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

    }
    if (loading) {
        LinearProgressIndicator(
            progress =  currentProgress ,
            modifier = Modifier.fillMaxWidth(),
        )
    }
    ObjectHolder.newMeal.foodName = foodName
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodInputTimeEaten(navController: NavHostController) {
    Button(onClick = {
        navController.navigate(
        route = "FOOD_INPUT_TIME_SCREEN"
        ) }
    ) {
        Text(text = ObjectHolder.newMeal.foodTimeEaten, fontSize =30.sp)

    }
    Spacer(modifier = Modifier.height(30.dp))
}
