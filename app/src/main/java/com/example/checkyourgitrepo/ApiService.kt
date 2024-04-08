package com.example.checkyourgitrepo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET


private val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/users/Tajtan/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val repoService = retrofit.create(ApiService::class.java)

interface ApiService{

    @GET("repos")
    suspend fun getRepos(): ReposResponse


}