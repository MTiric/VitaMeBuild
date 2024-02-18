package com.example.vitamebuild.generalFunctions

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.util.Log
import com.example.vitamebuild.ApiClient
import com.example.vitamebuild.FoodResponse
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.classes.Food
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.io.File
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import java.io.BufferedInputStream
import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels
import java.nio.file.Files
import java.nio.file.Paths
import org.json.XML

fun saveToXMLWaterDATA(context: android.content.Context) {
    val gson = Gson()
    val jsonWaterObject: String =
        "{\"Price\": {" +
     "    \"LineItems\": {" +
     "        \"LineItem\": {" +
     "            \"UnitOfMeasure\": \"EACH\", \"Quantity\": 2, \"ItemID\": \"ItemID\"" +
     "        }" +
     "    }," +
     "    \"Currency\": \"USD\"," +
     "    \"EnterpriseCode\": \"Ent erpriseCode\"" +
    "}}"
    Log.i("TestStoredValue", "jsonString: $jsonWaterObject")
    val xml: String = XML.toString(jsonWaterObject)
    Log.i("TestStoredValue", "xml: $xml")

    try {
        File(context.filesDir,"WaterHistory.xml").writeText(xml)
        Log.i("TestStoredValue", "saved data: ${xml}")
    } catch (e: Exception) {
        Log.i("TestStoredValue", "error: ${e.message}")
    }




}
fun saveToJsonFoodData(context: android.content.Context){
    val gson = Gson()


    val jsonFoodList: String = gson.toJson(ObjectHolder.globalMealHistoryList)
    Log.i("TestStoredValue", "jsonString: $jsonFoodList")
    try {
        File(context.filesDir,"FoodHistory.json").writeText(jsonFoodList)
        Log.i("TestStoredValue", "saved data: ${jsonFoodList}")
    } catch (e: Exception) {
        Log.i("TestStoredValue", "error: ${e.message}")
    }


}

suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(10)
    }
}

fun loadJsonFileFoodData(context: android.content.Context){
    val jsonString = File(context.filesDir,"FoodHistory.json").readText()

    val gson = Gson()
    val foodType = object : TypeToken<List<Food>>() {}.type
    ObjectHolder.globalMealHistoryList = gson.fromJson<List<Food>>(jsonString, foodType).toMutableList()
    Log.i("TestStoredValue", "jsonString on load: $jsonString")

}
fun getFoodList(searchTerm: String) {

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
fun getCurrentHourAsInt(): Int{
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.HOUR_OF_DAY)
}

fun getCurrentMinuteAsInt(): Int{
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.MINUTE)
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











