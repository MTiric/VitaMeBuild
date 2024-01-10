package com.example.vitamebuild

import com.example.vitamebuild.classes.Food
import com.example.vitamebuild.classes.FoodContentGlobal
import com.example.vitamebuild.classes.Settings
import com.example.vitamebuild.classes.Water
import okhttp3.OkHttpClient

object ObjectHolder {
    var newMeal = Food("")
    var waterIntake = Water(0)
    var settings = Settings(false)
    lateinit var foodApiSearch: FoodResponse
    var globalFoodContent = FoodContentGlobal("No content yet")
}
