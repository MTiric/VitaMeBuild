package com.example.vitamebuild

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.vitamebuild.classes.Food
import com.example.vitamebuild.ui.theme.VitaMeBuildTheme
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        val food = Food(
            foodName = "Bread", timeEaten = "Now", dateEaten = "Now")
        super.onCreate(savedInstanceState)
        setContent {
            VitaMeBuildTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(food.foodDateEaten)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextField(
    @StringRes label: Int,
    //@DrawableRes leadingIcon: Int, //reserved for the leading icon, design phase
    keyboardOptions: KeyboardOptions,
    value: String, //current value to display
    onValueChanged: (String) -> Unit, //callback lambda, this is triggered when the value changes, makes the state be updated elsewhere
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        singleLine = true,
        //leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val food = Food(
        foodName = "Bread", timeEaten = "Now", dateEaten = "Now")
    VitaMeBuildTheme {
        Greeting(food.foodDateEaten)
    }
}