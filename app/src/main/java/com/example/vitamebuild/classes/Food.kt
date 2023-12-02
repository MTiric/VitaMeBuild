package com.example.vitamebuild.classes

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime
import java.time.format.DateTimeFormatter


//Creates a class for food below
class Food (val foodName: String) {

    var rating: Int = 0
    var foodTimeEaten: String = "unknown"
    var foodDateEaten: String = "unknown"
    var foodPlace: String = "unknown"
    var foodAmountFullness: Int = 0
    var foodTastiness: Int = 0

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(
        foodName: String,
        timeEaten: String,
        dateEaten: String) : this(foodName){
            foodTimeEaten = when(timeEaten) {
                "Now" -> getCurrentTimeAsString()
                else -> timeEaten
            }
            foodDateEaten = when(dateEaten) {
                "Now" -> getCurrentTimeAsString()
                else -> dateEaten
            }


    }




    fun calculateFoodQualityRecent() {
        println("Food was eaten recently and I feel ...")
    }

    fun calculateFoodQualityDistant() {
        println("Food was eaten more than few hours ago and I feel ...")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentTimeAsString(): String {
        val currentTime = LocalTime.now() // Get the current time
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss") // Define the format

        return currentTime.format(formatter) // Convert time to string using the defined format
    }

}