package com.example.vitamebuild.classes

import com.example.vitamebuild.generalFunctions.getCurrentDateAsString
import com.example.vitamebuild.generalFunctions.getCurrentTimeAsString

open class Water (var miliLitresCount: Int){
    var waterTimeDrank: String = getCurrentTimeAsString()
    var waterDateDrank: String = getCurrentDateAsString()
    var glassesOfWater: Double = calculateMiliLitresToGlasses(this.miliLitresCount)

    private fun calculateMiliLitresToGlasses(miliLitresCount: Int): Double {
        return miliLitresCount/250.0
    }
    private fun addHalfGlassOfWater125ml() : Double {
        return this.glassesOfWater + 0.5
    }
    private fun addFullGlassOfWater250ml() : Double {
        return this.glassesOfWater + 1
    }
}