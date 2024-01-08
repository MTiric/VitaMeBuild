package com.example.vitamebuild.classes

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.vitamebuild.ApiClient
import com.example.vitamebuild.FoodResponse
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.generalFunctions.getCurrentDateAsString
import com.example.vitamebuild.generalFunctions.getCurrentHourAsInt
import com.example.vitamebuild.generalFunctions.getCurrentMinuteAsInt
import com.example.vitamebuild.generalFunctions.getCurrentTimeAsString
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Nutrition(var name: String, val weightAmountInGrams: Int){
    var calories: Int = 0
    var vitamins: MutableMap<String, Double> = mutableMapOf()

    fun calculateWeightToNutritiveInformation(){
        //calculates the weight to vitamins and
    }

    fun getVitamins(){
        //gets the vitamins for this object
    }
}

//Creates a class for food below
open class Food (var foodName: String) {

    var foodTimeEaten: String = getCurrentTimeAsString()
    var foodDateEaten: String = getCurrentDateAsString()
    var foodPlace: String = ""
    var foodAmountFullness: Float = 0f
    var foodTastiness: Float = 0f
    var rating: Float = 0f
    var amountInGrams: Int = 0
    var calories: Int = calculateWeightToCalories(foodName, amountInGrams)
    var vitamins: MutableMap<String, Double> = getVitamins(foodName)


    fun getFood() {
        val searchTerm = "Pizza"

        val call = ApiClient.apiServiceFoodApi.getFood(searchTerm)

        call.enqueue(object : Callback<FoodResponse> {
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
    }
    private fun getVitamins(foodName: String): MutableMap<String, Double> {
        var foodVitamins: MutableMap<String, Double> = mutableMapOf("" to 0.0)
        //fetch api data and put all data inside foodVitamins
        return foodVitamins
    }



    private fun calculateWeightToCalories(foodName: String, amountInGrams: Int): Int {
        //will calculate calories based on food and weight from API
        return 0
    }


    constructor(
        foodName: String,
        timeEaten: String,
        dateEaten: String) : this(foodName){
            foodTimeEaten = when(timeEaten) {
                "Now" -> getCurrentTimeAsString()
                else -> timeEaten
            }
            foodDateEaten = when(dateEaten) {
                "Now" -> getCurrentDateAsString()
                else -> dateEaten
            }


    }




    fun calculateFoodQualityRecent() {
        println("Food was eaten recently and I feel ...")
    }

    fun calculateFoodQualityDistant() {
        println("Food was eaten more than few hours ago and I feel ...")
    }



}


