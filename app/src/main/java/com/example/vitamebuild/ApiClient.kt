package com.example.vitamebuild
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object RetrofitClientJsonPlaceHolderTesting {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object RetroFitClientFoodApi {
    private const val BASE_URL = "https://api.nal.usda.gov/fdc/v1/foods/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object ApiClient {
    val apiServiceJsonTest: ApiService by lazy {
        RetrofitClientJsonPlaceHolderTesting.retrofit.create(ApiService::class.java)
    }
    val apiServiceFoodApi: ApiService by lazy {
        RetroFitClientFoodApi.retrofit.create(ApiService::class.java)
    }
}


interface ApiService {
    @GET("posts/{id}")
    fun getPostById(@Path("id") postId: Int): Call<Post>

    @GET("get-user/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @GET("search?api_key=c0muutetuLERr5nqPNkaUQQfqWVyAqeOFNeabVJY")
    fun getFood(@Query("query") searchTerm: String): Call<FoodResponse>


}

data class FoodResponse(
    val totalHits: Int,
    val foods: List<FoodContent>
)
data class FoodContent(
    val fdcId: Int,
    val description: String,
    val dataType: String,
    val tags: List<String>,
    val foodCategory: String,
    val foodCategoryId: Int

)
data class User (
    val id: Int
)


data class Post(
    //val userId: String,
    //val id: String,
    val title: String,
    //val body: String
)
