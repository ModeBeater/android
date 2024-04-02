package com.example.dogs

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApiService {
    @GET("dogs")
    fun getDogs(@Query("name") name: String, @Query("X-Api-Key") apiKey: String): Call<List<Dog>>
}