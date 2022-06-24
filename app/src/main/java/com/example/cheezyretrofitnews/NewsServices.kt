package com.example.cheezyretrofitnews

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/everything?q=tesla&from=2022-02-18&sortBy=publishedAt&apiKey=b7835d2e6c674e48bfed6b982ccc5db1
//https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=b7835d2e6c674e48bfed6b982ccc5db1

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "b7835d2e6c674e48bfed6b982ccc5db1"
interface NewsInterface{

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadLines(@Query("country")  country:String, @Query("page") page:Int ):Call<News>


}//singleton class
object NewsServices {
    var newsInterface:NewsInterface
    init {
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInterface=retrofit.create(NewsInterface::class.java)
    }

}