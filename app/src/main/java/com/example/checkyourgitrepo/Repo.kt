package com.example.checkyourgitrepo

data class Repos(

    val name: String,
    val language: String
)

data class ReposResponse(
    val repos: List<Repos>
)

data class Repo(
    val languages: List<String>
)