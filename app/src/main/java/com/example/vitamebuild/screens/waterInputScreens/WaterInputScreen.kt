package com.example.vitamebuild.screens.waterInputScreens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.generalFunctions.saveToXMLWaterDATA
import com.example.vitamebuild.graphicalInterfaces.EditTextField
import com.example.vitamebuild.graphicalInterfaces.MyScaffold

@Composable
fun WaterInputScreen(navController: NavHostController) {
    var context = LocalContext.current
    MyScaffold(navController,
        currentScreenName = R.string.log_water_intake,
        previousScreenRoute = "MAIN_SCREEN") {
            WaterInputDetailed(navController, context)
    }
}

@Composable
fun WaterInputDetailed(navController: NavHostController, context: Context) {
    MyStyleColumn(textContent = R.string.today_I_drank) {
        WaterInputData()
        InputScreenText(textContent = R.string.of_water)

        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            saveToXMLWaterDATA(context)
            //navController.navigate("MAIN_SCREEN")

        },
            modifier = Modifier
                .height(72.dp)
                .fillMaxWidth()) {
            Text(stringResource(R.string.submit_button))
        }

        Spacer(modifier = Modifier.height(200.dp))
    }

}

@Composable
fun WaterInputData() {
    Row (horizontalArrangement = Arrangement.Center){
        SegmentField(
            segmentTitle = "Mililiters",
            segmentText = "+100ml"
        )
        Column {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "description",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft
                ,
                contentDescription = "description",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
        SegmentField(
            segmentTitle = "Glasses",
            segmentText = "+1 glass(300mL)"
        )
    }
}

@Composable
fun Segment(segmentTitle: String, segmentText: String, modifier: Modifier = Modifier){
    Column (modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = segmentTitle,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = segmentText,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun SegmentedScreenModified(firstSegment: String, firstSegmentTitle: String,
                            secondSegment: String, secondSegmentTitle: String,
                            thirdSegment: String, thirdSegmentTitle: String,
                            fourthSegment: String, fourthSegmentTitle: String,
                            modifier: Modifier = Modifier){
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()){
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)){
            Segment(firstSegmentTitle, firstSegment,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f)
                    .background(color = Color(0xFFEADDFF))
                    .padding(16.dp))
            Segment(secondSegmentTitle, secondSegment,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(color = Color(0xFFD0BCFF))
                    .padding(16.dp))
        }
    }
}

@Composable
fun SegmentField(segmentTitle: String, segmentText: String, modifier: Modifier = Modifier){
    Column (modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        var water by remember { mutableStateOf(0) }

        EditTextField(
            value = water.toString(),
            onValueChanged = { water = it.toInt() },
            label = R.string.water_intake,
            //leadingIcon = R.drawable.template,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            singleLineState = true,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .width(120.dp)
                .align(Alignment.End)
        )
        Button(onClick = {
            if (segmentTitle == "Mililiters"){
                water += 100
                ObjectHolder.waterIntake.miliLitresCount = water
                Log.i("Test_Waters", "${ObjectHolder.waterIntake.miliLitresCount}")

            }
        }) {
            Text(
                text = segmentText
            )
        }
        Button(onClick = {
            if (segmentTitle == "Mililiters"){
                water -= 100
                Log.i("Test_Waters", "$water")

            }
        }) {
            Text(
                text = "Undo"
            )
        }

    }
}

@Composable
fun MyStyleColumn(
    textContent: Int,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp, vertical = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        InputScreenText(textContent = textContent)


        content()
    }
}

@Composable
fun InputScreenText(textContent: Int) {
    Text(
        text = stringResource(textContent),
        style = MaterialTheme.typography.displaySmall,
        modifier = Modifier
            .padding(bottom = 16.dp, top = 40.dp)
    )
}
