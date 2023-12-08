package com.example.vitamebuild.graphicalInterfaces

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vitamebuild.R
import com.example.vitamebuild.graphicalInterfaces.navigation.NavigationSegment

@Composable
fun BottomAppBarCustom(navController: NavHostController){
    BottomAppBar(
        actions = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                GoToHomeScreenNavigationButton(navController)
                GoToHistoryScreenNavigationButton(navController)
                GoToPersonalDataScreenNavigationButton(navController)
                GoToGoalsScreenNavigationButton(navController)
            }
        },
        )
}

@Composable
fun GoToGoalsScreenNavigationButton(navController: NavHostController) {
    NavigationSegment(
        navController,
        navRoute = "GOALS_SCREEN",
        Icons.Default.Face,
        segmentText = stringResource(id = R.string.goals),
        description = stringResource(id = R.string.settings)

    )

}

@Composable
fun GoToPersonalDataScreenNavigationButton(navController: NavHostController) {
    NavigationSegment(
        navController,
        navRoute = "PERSONAL_DATA_SCREEN",
        Icons.Default.Face,
        segmentText = stringResource(id = R.string.personal_data),
        description = stringResource(id = R.string.personal_data)

    )

}

@Composable
fun GoToHomeScreenNavigationButton(navController: NavHostController) {
    NavigationSegment(
        navController,
        navRoute = "MAIN_SCREEN",
        Icons.Default.Home,
        segmentText = stringResource(id = R.string.home),
        description = stringResource(id = R.string.home)

    )

}

@Composable
fun GoToHistoryScreenNavigationButton(navController: NavHostController) {
    NavigationSegment(
            navController,
    navRoute = "LOG_HISTORY_SCREEN",
    Icons.Default.DateRange,
    segmentText = stringResource(id = R.string.history),
    description = stringResource(id = R.string.history)

    )
}
