package com.example.vitamebuild

import com.example.vitamebuild.classes.Food
import com.example.vitamebuild.classes.Settings
import com.example.vitamebuild.classes.Water

object ObjectHolder {
    var newMeal = Food("")
    var waterIntake = Water(0)
    var settings = Settings(false)
}
