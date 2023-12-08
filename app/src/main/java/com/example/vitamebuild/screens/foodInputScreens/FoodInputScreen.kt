package com.example.vitamebuild.screens.foodInputScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vitamebuild.DefaultMealHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.classes.Food
import com.example.vitamebuild.graphicalInterfaces.EditTextField
import com.example.vitamebuild.graphicalInterfaces.MyScaffold




@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoodInputScreen(navController: NavHostController) {
    MyScaffold(navController,
        currentScreen = R.string.meal_input_screen,
        previousScren = "MAIN_SCREEN") {
        FoodInputDetailed(navController)
    }
}


@Composable
fun FoodInputDetailed(navController: NavHostController) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp, vertical = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = stringResource(R.string.today_I_ate),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        //Input for the name of the food
        FoodInputFoodName()
        Text(
            text = stringResource(R.string.at_around),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
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
        SubmitFoodButton(navController)

        Spacer(modifier = Modifier.height(200.dp))
    }
}

@Composable
fun SubmitFoodButton(navController: NavHostController) {
    Button(onClick = {
        navController.navigate("IS_THIS_YOUR_MEAL")

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

    DefaultMealHolder.defaultMeal.rating = rating
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

    DefaultMealHolder.defaultMeal.foodTastiness = tastiness
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

    DefaultMealHolder.defaultMeal.foodAmountFullness = amountFullness
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

    DefaultMealHolder.defaultMeal.amountInGrams = amountInGrams.toIntOrNull() ?: 0
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

    DefaultMealHolder.defaultMeal.foodPlace = place
}

@Composable
fun FoodInputDateEaten() {
    var dateEaten by remember { mutableStateOf(DefaultMealHolder.defaultMeal.foodDateEaten) }

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
    DefaultMealHolder.defaultMeal.foodDateEaten = dateEaten
}

@Composable
fun FoodInputFoodName() {
    var foodName by remember { mutableStateOf(DefaultMealHolder.defaultMeal.foodName) }

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
            .fillMaxWidth(),
    )
    DefaultMealHolder.defaultMeal.foodName = foodName
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodInputTimeEaten(navController: NavHostController) {
    Button(onClick = {
        navController.navigate(
        route = "FOOD_INPUT_TIME_SCREEN"
        ) }
    ) {
        Text(text = DefaultMealHolder.defaultMeal.foodTimeEaten, fontSize =30.sp)

    }
    Spacer(modifier = Modifier.height(30.dp))
}
