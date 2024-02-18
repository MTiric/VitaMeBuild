package com.example.vitamebuild.graphicalInterfaces

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.vitamebuild.MainActivity
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.screens.historyScreens.timeSinceLastMeal
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object HomeScreenWidget: GlanceAppWidget() {

    @Composable
    override fun Content() {
        val text = "Log a meal"
        Column (
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color.DarkGray),
            verticalAlignment = androidx.glance.layout.Alignment.Vertical.CenterVertically,
            horizontalAlignment = androidx.glance.layout.Alignment.Horizontal.CenterHorizontally
        ){
            var lastMeal by remember { mutableStateOf("Last meal?") }
            Button(text = "Log a meal",
                onClick = actionRunCallback(OpenVitaMeApp::class.java)
            )


        }
    }

}

class SimpleWidgetReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = HomeScreenWidget
}

class OpenVitaMeApp: ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
        ObjectHolder.isFoodInput = true
        GlobalScope.launch {
            var lastRecordedMeal = timeSinceLastMeal()
            Log.i("Test_Response", "$lastRecordedMeal")
        }
    }
}

class CheckLastMeal: ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
        ObjectHolder.isFoodInput = true
        GlobalScope.launch {
            var lastRecordedMeal = timeSinceLastMeal()
            Log.i("Test_Response", "$lastRecordedMeal")
        }
    }
}