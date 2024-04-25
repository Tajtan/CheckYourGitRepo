package com.example.checkyourgitrepo

data class Repo(
    val name: String,
    val language: String?
)

data class Language(
    val language: String,
    val bytes: Int,
    //val map: Map<String, Int>
)