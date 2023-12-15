package com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.classes.Food
import java.time.format.TextStyle

@Composable
fun GoToLogMealScreenButton(
    navController: NavHostController) {
    MyButton(
        textID = R.string.log_meal,
    ) {
        ObjectHolder.newMeal = Food("")
        navController.navigate(route = "FOOD_INPUT_SCREEN")
    }
}

@Composable
fun MyButton(
    textID: Int,
    onClick: () -> Unit

) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = stringResource(id = textID),
            fontSize = ObjectHolder.settings.fontSizeButtons.sp
        )
    }
}
