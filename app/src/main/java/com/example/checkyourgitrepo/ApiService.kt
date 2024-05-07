package com.example.checkyourgitrepo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


private val retrofit = Retrofit
    .Builder()
    .baseUrl("https://api.github.com")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val repoService = retrofit.create(ApiService::class.java)

interface ApiService{

    @GET("users/{username}/repos")
    suspend fun getRepos(@Path("username") username: String): List<Repo>


    @GET("repos/{username}/{repo}/languages")
    suspend fun getLanguages(
        @Path("username") username: String,
        @Path("repo") repo: String
    ): Map<String, Int>

}