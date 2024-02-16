package com.example.vitamebuild.screens.personalDataScreens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.classes.Hasher
import com.example.vitamebuild.graphicalInterfaces.EditTextField
import com.example.vitamebuild.graphicalInterfaces.MyScaffold
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@Composable
fun SignInScreen(navController: NavHostController) {
    MyScaffold(
        navController,
        currentScreenName = R.string.signIn,
        previousScreenRoute = "PERSONAL_DATA_SCREEN"
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var passwordInvisible by remember { mutableStateOf(true) }

            Row {
                EditTextField(
                    value = email,
                    onValueChanged = { email = it },
                    label = R.string.email,
                    //leadingIcon = R.drawable.template,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    singleLineState = true,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                        .weight(1f),

                    )
            }
            Row {
                EditTextField(
                    value = password,
                    onValueChanged = { password = it },
                    label = R.string.password,
                    //leadingIcon = R.drawable.template,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLineState = true,
                    applyVisualTransformation = passwordInvisible,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                        .weight(1f),

                    )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Checkbox(
                    checked = passwordInvisible,
                    onCheckedChange = { isChecked ->
                        passwordInvisible = isChecked
                    },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "Show Password")
            }

            Row {
                var scope = rememberCoroutineScope()
                var hashedPass by remember { mutableStateOf("") }
                var isLoading by remember { mutableStateOf(false) } // State to manage loading state

                Button(colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    onClick = {
                        scope.launch {
                            isLoading = true // Set loading state true when button is clicked
                            ObjectHolder.globalUser.authorized = false
                            val httpClient = HttpClient(Android) {
                                install(JsonFeature) {
                                    serializer = KotlinxSerializer(Json {
                                        ignoreUnknownKeys = true
                                    })
                                }
                            }

                            for (i in 0 until ObjectHolder.alphabet.length) {
                                try {
                                    Log.i("Test_Response", "${ObjectHolder.alphabet[i]}")
                                    val token: String = httpClient.get<String> {
                                        url(
                                            " ${ObjectHolder.globalURLRESTAPI}/getUserToken/" + "${email}"
                                        )
                                    }
                                    var pepper: String = ObjectHolder.alphabet[i].toString()

                                    hashedPass = Hasher().generateHash(password, email, pepper)
                                    var jsonUserDataString: String =
                                        "{\"username\":\"$email\",\"password\":\"$hashedPass\", \"token\":\"$token\"}"

                                    Log.i("Test_Response", "${jsonUserDataString}")
                                    val authorized =
                                        httpClient.post<String>("${ObjectHolder.globalURLRESTAPI}/signIn") {
                                            headers {
                                                append(HttpHeaders.ContentType, "application/json")
                                            }
                                            body = jsonUserDataString
                                        }
                                    val gson = Gson()
                                    val jsonResponseObject =
                                        gson.fromJson(authorized, JsonObject::class.java)

                                    ObjectHolder.globalUser.authorized =
                                        jsonResponseObject.get("authorized").asBoolean
                                    ObjectHolder.globalUser.uniqueToken = token
                                    ObjectHolder.globalUser.userMailAddress = email
                                    Log.i(
                                        "Test_Response",
                                        "post successful, user: ${ObjectHolder.globalUser.userMailAddress}"
                                    )

                                    navController.navigate(route = "MAIN_SCREEN")


                                } catch (e: Exception) {
                                    Log.i("Test_Response", "Unsuccessful post: ${e.message}")
                                }
                                if (ObjectHolder.globalUser.authorized) {
                                    isLoading = false
                                    break
                                }
                            }

                        }
                    }) {
                    if (isLoading) {
                        CircularProgressIndicator() // Show CircularProgressIndicator when isLoading is true
                    } else {
                        Text(text = stringResource(id = R.string.signIn))
                    }
                }

            }

        }

    }
}