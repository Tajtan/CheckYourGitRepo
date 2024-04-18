package com.example.checkyourgitrepo

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {

    var username: String = "Tajtan"

    private val _reposState = mutableStateOf(ReposState())
    val reposState: State<ReposState> = _reposState

    private val _languagesState = mutableStateOf(LanguageState())
    val languageState: State<LanguageState> = _languagesState

    init {
        //fetchRepos()
        fetchLanguages()
    }


    private fun fetchRepos(){
        viewModelScope.launch {
            try {
                val response = repoService.getRepos(username)
                _reposState.value = _reposState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )

            }catch (e: Exception){
                _reposState.value = _reposState.value.copy(
                    loading = false,
                    error = "Error fetching Repos: ${e.message}"
                )
            }
        }
    }

    private fun fetchLanguages(){
        viewModelScope.launch {
            try {
                val response = repoService.getLanguages()
                Log.e("languageResponse", response.toString())
                _languagesState.value = _languagesState.value.copy(
                    list = response.map { (Language(it.key, it.value)) },
                    loading = false,
                    error = null
                )
                Log.e("languageResponse", response.toString())


            }catch (e: Exception){
                _languagesState.value = _languagesState.value.copy(
                    loading = false,
                    error = "Error fetching Repos: ${e.message}"
                )
                Log.e("repo error",e.toString())
            }
        }
    }

    data class ReposState(
        val loading: Boolean = true,
        val list: List<Repos> = emptyList(),
        val error: String? = null
    )

    data class LanguageState(
        val loading: Boolean = true,
        val list: List<Language> = emptyList(),
        val error: String? = null
    )
}