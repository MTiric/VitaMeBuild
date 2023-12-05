package com.example.vitamebuild.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vitamebuild.R
import com.example.vitamebuild.classes.Food
import com.example.vitamebuild.graphicalInterfaces.EditTextField
import com.example.vitamebuild.screens.MealHolder.meal

object MealHolder {
    var meal = Food("Pizza")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodInputScreen(navController: NavHostController) {
    var foodName by remember { mutableStateOf("") }
    var timeEaten by remember { mutableStateOf("") }
    var dateEaten by remember { mutableStateOf("") }
    var place by remember { mutableStateOf("") }
    var amountFullness by remember { mutableFloatStateOf(0f) }
    var tastiness by remember { mutableFloatStateOf(0f) }
    var rating by remember { mutableFloatStateOf(0f) }
    var amountInGrams by remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.food_title_generic),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        //Input for the name of the food
        EditTextField(
            value = foodName,
            onValueChanged = { foodName = it },
            label = R.string.food_name,
            //leadingIcon = R.drawable.template,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
        )
        EditTextField(
            value = amountInGrams,
            onValueChanged = { amountInGrams = it },
            label = R.string.food_amount_in_grams,
            //leadingIcon = R.drawable.template,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
        )
        //Input field for the
        EditTextField(
                value = timeEaten,
                onValueChanged = { timeEaten = it },
                label = R.string.food_time_eaten,
                //leadingIcon = R.drawable.template,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth(),
            )
        EditTextField(
            value = dateEaten,
            onValueChanged = { dateEaten = it },
            label = R.string.food_date_eaten,
            //leadingIcon = R.drawable.template,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
        )
        EditTextField(
            value = place,
            onValueChanged = { place = it },
            label = R.string.food_place,
            //leadingIcon = R.drawable.template,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
        )
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


        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            navController.navigate("IS_THIS_YOUR_MEAL")
            meal.foodName = foodName
            meal.foodTimeEaten = timeEaten
            meal.foodDateEaten = dateEaten
            meal.foodPlace = place
            meal.foodAmountFullness = amountFullness
            meal.foodTastiness = tastiness
            meal.rating = rating
            meal.amountInGrams = amountInGrams.toIntOrNull() ?: 0

                         },
            modifier = Modifier
                .height(72.dp)
                .fillMaxWidth()) {
            Text(stringResource(R.string.submit_button))
        }

        Spacer(modifier = Modifier.height(200.dp))
    }
}