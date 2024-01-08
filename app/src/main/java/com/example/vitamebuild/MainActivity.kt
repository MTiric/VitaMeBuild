package com.example.vitamebuild

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.vitamebuild.graphicalInterfaces.navigation.CustomNavHost
import com.example.vitamebuild.ui.theme.VitaMeBuildTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            VitaMeBuildTheme {

                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    ObjectHolder.settings  = ObjectHolder.settings.loadFromIniFile(context)
                    val navController = rememberNavController()
                    //CustomNavHost(navController = navController)
                    Column {
                        Button(onClick = {
                            val postId = 1
                            val call = ApiClient.apiServiceJsonTest.getPostById(postId)

                            call.enqueue(object : Callback<Post> {
                                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                                    if (response.isSuccessful) {
                                        val post = response.body()
                                        if (post != null) {
                                            Log.i("Test_Response", "onResponse: ${post.title}")
                                        }
                                    } else {
                                        Log.i("Test_Response", "onResponse: it works NOT")
                                    }
                                }

                                override fun onFailure(call: Call<Post>, t: Throwable) {
                                    Log.i("Test_Response", "onResponse: it no worky")
                                }

                            })

                        }) {
                            Text( text = "Buttom")


                        }

                        Button(onClick = {

                            val searchTerm = "Pizza"

                            val call = ApiClient.apiServiceFoodApi.getFood(searchTerm)

                            call.enqueue(object : Callback<FoodResponse>{
                                override fun onResponse(
                                    call: Call<FoodResponse>,
                                    response: Response<FoodResponse>
                                ) {
                                    if(response.isSuccessful) {
                                        val food = response.body()
                                        if (food != null) {
                                            ObjectHolder.foodApiSearch = food
                                            for(foodInList in ObjectHolder.foodApiSearch.foods){
                                                Log.i("Test_Response", "onResponse: ${foodInList.description}")
                                            }
                                        }
                                    }
                                }

                                override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                                    Log.i("Test_Response", "onResponse: it no worky", t)
                                }

                            })


                        }) {
                            Text(text = "Button for list")

                        }

                        for (i in 1..5) {
                            Text(text = "Hello world")
                        }

                    }



                }
            }
        }


    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VitaMeBuildTheme {
        val navController = rememberNavController()
        CustomNavHost(navController = navController)
    }
}