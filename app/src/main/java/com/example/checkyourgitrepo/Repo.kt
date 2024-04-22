package com.example.checkyourgitrepo

import com.google.gson.annotations.SerializedName

data class Repos(
    val name: String,
    val language: String?
)

data class Language(
    val language: String,
    val bytes: Int,
    //val map: Map<String, Int>
)