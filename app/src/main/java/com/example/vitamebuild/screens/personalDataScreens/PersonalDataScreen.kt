package com.example.vitamebuild.screens.personalDataScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.graphicalInterfaces.EditTextField
import com.example.vitamebuild.graphicalInterfaces.MyScaffold

@Composable
fun PersonalDataScreen(navController: NavHostController) {
    MyScaffold(navController,
        currentScreenName = R.string.personal_data,
        previousScreenRoute = "MAIN_SCREEN") {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ){
            if (!ObjectHolder.globalUser.authorized){
                Button(onClick = { navController.navigate(route = "SIGN_IN_SCREEN") }) {
                    Text(text = "Sign in")
                }
                Button(onClick = { navController.navigate(route = "REGISTER_SCREEN") }) {
                    Text(text = "Register")
                }
            } else {
                UserMailSegmentRow()
                UserNickNameSegment()
                UserWeightSegment()
                UserWeightPoundsSegment()
                Row {
                    ObjectHolder.globalUser.heightInches = ObjectHolder.globalUser.height?.let {
                        ObjectHolder.globalUser.convertToInches( it )
                    }
                    TextField(
                        value = ObjectHolder.globalUser.heightInches.toString(),
                        onValueChange = { ObjectHolder.globalUser.heightInches = it.toDouble() },
                        label = { Text(stringResource(id = R.string.heightInches)) },
                        enabled = false, // This makes the TextField disabled
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxWidth()
                            .weight(1f),

                        )
                }

            }

        }
        
    }

}

@Composable
fun UserWeightPoundsSegment() {
    Row {
        ObjectHolder.globalUser.weightPounds = ObjectHolder.globalUser.weight?.let {
            ObjectHolder.globalUser.convertToPounds( it )
        }
        TextField(
            value = ObjectHolder.globalUser.weightPounds.toString(),
            onValueChange = { ObjectHolder.globalUser.weightPounds = it.toDouble() },
            label = { Text(stringResource(id = R.string.weightInPounds)) },
            enabled = false, // This makes the TextField disabled
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .weight(1f),

            )
    }
}

@Composable
fun UserWeightSegment() {
    Row {
        TextField(
            value = ObjectHolder.globalUser.weight.toString(),
            onValueChange = { ObjectHolder.globalUser.weight = it.toDouble() },
            label = { Text(stringResource(id = R.string.weight)) },
            enabled = false, // This makes the TextField disabled
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .weight(1f),

            )
    }
}

@Composable
fun UserNickNameSegment() {
    Row {
        TextField(
            value = ObjectHolder.globalUser.nickname,
            onValueChange = { ObjectHolder.globalUser.nickname = it },
            label = { Text(stringResource(id = R.string.nickname)) },
            enabled = false, // This makes the TextField disabled
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .weight(1f),

            )
    }
}

@Composable
fun UserMailSegmentRow() {
    Row {
        TextField(
            value = ObjectHolder.globalUser.userMailAddress,
            onValueChange = { ObjectHolder.globalUser.userMailAddress = it },
            label = { Text(stringResource(id = R.string.email)) },
            enabled = false, // This makes the TextField disabled
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .weight(1f),

            )
    }
}

