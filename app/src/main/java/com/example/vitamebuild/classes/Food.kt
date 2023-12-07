package com.example.vitamebuild.classes

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.vitamebuild.generalFunctions.getCurrentHourAsInt
import com.example.vitamebuild.generalFunctions.getCurrentMinuteAsInt
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

    var foodTimeEaten: String = ""
    var foodDateEaten: String = ""
    var foodPlace: String = ""
    var foodAmountFullness: Float = 0f
    var foodTastiness: Float = 0f
    var rating: Float = 0f
    var amountInGrams: Int = 0
    var nutritiveInformation: Nutrition = Nutrition(foodName, 0)


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


    fun getCurrentTimeAsString(): String {
        var currentHourString = ""
        var currentMinuteString = ""

        if(getCurrentHourAsInt()<10){
            currentHourString = "0" + getCurrentHourAsInt().toString()
        } else {
            currentHourString = getCurrentHourAsInt().toString()
        }

        if(getCurrentMinuteAsInt()<10){
            currentMinuteString = "0" + getCurrentMinuteAsInt().toString()
        } else {
            currentMinuteString = getCurrentMinuteAsInt().toString()
        }

        return "$currentHourString:$currentMinuteString"
    }

    fun getCurrentDateAsString(): String {
        val currentDate = LocalDate.now() // Get the current time
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // Define the format

        return currentDate.format(formatter) // Convert time to string using the defined format
    }
}


