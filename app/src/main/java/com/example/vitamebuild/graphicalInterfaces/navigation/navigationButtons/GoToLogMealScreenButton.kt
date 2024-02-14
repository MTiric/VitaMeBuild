package com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
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
    if (ObjectHolder.settings.buttonStyle == 1) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            if (ObjectHolder.settings.fontStyleButtons == 1) {
                Text(
                    text = stringResource(id = textID),
                    fontSize = ObjectHolder.settings.fontSizeButtons.sp,
                    fontFamily = FontFamily.Default
                )
            } else if (ObjectHolder.settings.fontStyleButtons == 2) {
                Text(
                    text = stringResource(id = textID),
                    fontSize = ObjectHolder.settings.fontSizeButtons.sp,
                    fontFamily = FontFamily.Cursive
                )
            } else if (ObjectHolder.settings.fontStyleButtons == 3) {
                Text(
                    text = stringResource(id = textID),
                    fontSize = ObjectHolder.settings.fontSizeButtons.sp,
                    fontFamily = FontFamily.Serif
                )
            }
        }
    } else if ( ObjectHolder.settings.buttonStyle == 2) {
        ElevatedButton(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            if (ObjectHolder.settings.fontStyleButtons == 1) {
                Text(
                    text = stringResource(id = textID),
                    fontSize = ObjectHolder.settings.fontSizeButtons.sp,
                    fontFamily = FontFamily.Default
                )
            } else if (ObjectHolder.settings.fontStyleButtons == 2) {
                Text(
                    text = stringResource(id = textID),
                    fontSize = ObjectHolder.settings.fontSizeButtons.sp,
                    fontFamily = FontFamily.Cursive
                )
            } else if (ObjectHolder.settings.fontStyleButtons == 3) {
                Text(
                    text = stringResource(id = textID),
                    fontSize = ObjectHolder.settings.fontSizeButtons.sp,
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}
