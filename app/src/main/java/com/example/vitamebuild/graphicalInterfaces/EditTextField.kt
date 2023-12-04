package com.example.vitamebuild.graphicalInterfaces

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextField(
    @StringRes label: Int,
    //@DrawableRes leadingIcon: Int, //reserved for the leading icon, design phase
    keyboardOptions: KeyboardOptions,
    value: String, //current value to display
    onValueChanged: (String) -> Unit, //callback lambda, this is triggered when the value changes, makes the state be updated
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