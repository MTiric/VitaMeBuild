package com.example.vitamebuild.classes



class Person (var nickname: String){
    var userMailAddress: String = ""
    var uniqueToken: String = ""
    var height: Double? = 0.0
    var heightInches: Double? = height?.let { convertToInches(it) }
    var weight: Double? = 0.0
    var weightPounds: Double? = weight?.let { convertToPounds(it) }
    var idealWeight: Double = WeightGoal()
    var age: Int? = 0
    var sex: String? = ""
    var generalHappiness: Double = 0.0
    var eatingStatus: String = ""
    var lifestyle: String = ""
    var authorized: Boolean = false
    var mealHistory: MutableList<Food> = mutableListOf()


    fun WeightGoal(): Double {
        //gives guidance for weight loss
        return 50+(0.91*(height?.minus(152.4)!!))
    }

    fun convertToPounds(weight: Double): Double {
        return weight*2.20462262
    }

    fun convertToInches(height: Double): Double {
        return height/2.54
    }




}