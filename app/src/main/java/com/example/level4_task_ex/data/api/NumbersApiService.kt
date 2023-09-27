package com.example.level4_task_ex.data.api

import com.example.level4_task_ex.data.model.Numbers
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApiService {
    // The GET method needed to retrieve a random number trivia.
    @GET("/random/{numberType}?json")
    suspend fun getRandomNumber(@Path("numberType") numberType: String): Numbers
}