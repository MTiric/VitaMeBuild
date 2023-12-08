package com.example.vitamebuild.graphicalInterfaces

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.vitamebuild.R

@Composable
fun SettingsIconButton(navController: NavHostController) {
    IconButton(
        onClick = {
            navController.navigate(
                route = "SETTINGS_SCREEN"
            )
        }
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(id = R.string.settings),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}