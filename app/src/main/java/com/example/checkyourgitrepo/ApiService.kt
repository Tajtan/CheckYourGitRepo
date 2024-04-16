package com.example.checkyourgitrepo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path


private val retrofit = Retrofit
    .Builder()
    .baseUrl("https://api.github.com/users/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val repoService = retrofit.create(ApiService::class.java)

interface ApiService{

    @GET("{username}/repos")
    suspend fun getRepos(@Path("username") username: String): List<Repos>


}