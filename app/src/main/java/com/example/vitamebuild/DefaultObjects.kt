package com.example.vitamebuild

import com.example.vitamebuild.classes.Food
import com.example.vitamebuild.classes.FoodContentGlobal
import com.example.vitamebuild.classes.Person
import com.example.vitamebuild.classes.Settings
import com.example.vitamebuild.classes.Water
import okhttp3.OkHttpClient
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

object ObjectHolder {
    var newMeal = Food("")
    var waterIntake = Water(0)
    var settings = Settings(false)
    lateinit var foodApiSearch: FoodResponse
    var globalFoodContent = FoodContentGlobal("No content yet")
    var globalMealHistoryList: MutableList<Food> = mutableListOf()
    var globalIndex: Int = 0
    val threadPool = Executors.newFixedThreadPool(5) as ThreadPoolExecutor
    var globalUser = Person("Guest")
}
