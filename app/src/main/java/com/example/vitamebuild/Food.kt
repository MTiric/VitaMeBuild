package com.example.vitamebuild

class Food (){
    var foodName = "Bread"
    var foodAmount = 0
    var foodTimeEaten = 0
    var tastiness = 0


    fun calculateFoodQualityRecent() {
        println("Food was eaten recently and I feel ...")
    }

    fun calculateFoodQualityDistant() {
        println("Food was eaten more than few hours ago and I feel ...")
    }
}