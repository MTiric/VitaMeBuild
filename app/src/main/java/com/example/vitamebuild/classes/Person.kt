package com.example.vitamebuild.classes



class Person (var nickname: String){
    var userMailAddress: String = ""
    var uniqueToken: String = ""
    var height: Double = 0.0
    var heightInches: Double= convertToInches(height)
    var weight: Double = 0.0
    var weightPounds: Double = convertToPounds(weight)
    var idealWeight: Double = WeightGoal()
    var age: Int = 0
    var sex: String = ""
    var generalHappiness: Double = 0.0
    var eatingStatus: String = ""
    var lifestyle: String = ""
    var authorized: Boolean = false
    var mealHistory: MutableList<Food> = mutableListOf()


    fun WeightGoal(): Double {
        //gives guidance for weight loss
        return 50+(0.91*(height-152.4))
    }

    private fun convertToPounds(weight: Double): Double {
        return weight*2.20462262
    }

    private fun convertToInches(height: Double): Double {
        return height/2.54
    }




}