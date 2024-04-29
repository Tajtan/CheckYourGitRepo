package com.example.checkyourgitrepo

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {

    private val _reposState = mutableStateOf(ReposState())
    val reposState: State<ReposState> = _reposState

    private val _languagesState = mutableStateOf(LanguageState())
    val languageState: State<LanguageState> = _languagesState

    var username by mutableStateOf("")

    fun onUsernameChanged(newString: String){
        username = newString
    }

    init {
        //fetchRepos()
        //fetchLanguages()
    }


    fun fetchRepos(){
        viewModelScope.launch {
            try {
                _reposState.value = _reposState.value.copy(loading = true)

                val initialResponse = repoService.getRepos(username)
                val response = initialResponse.map { repo ->
                    if (repo.language == null) {
                        repo.copy(language = "No languages listed")
                    } else {
                        repo
                    }
                }
                _reposState.value = _reposState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
                //Log.e("repoResponse", response.toString())

            }catch (e: Exception){
                _reposState.value = _reposState.value.copy(
                    loading = false,
                    error = "Error fetching Repos: ${e.message}"
                )
                //Log.e("repo error",e.toString())
            }
        }
    }

    fun fetchLanguages(){
        viewModelScope.launch {
            try {
                val response = repoService.getLanguages()
                //Log.e("languageResponse", response.toString())
                _languagesState.value = _languagesState.value.copy(
                    list = response.map { (Language(it.key, it.value)) },
                    loading = false,
                    error = null
                )
                //Log.e("languageResponse", response.toString())


            }catch (e: Exception){
                _languagesState.value = _languagesState.value.copy(
                    loading = false,
                    error = "Error fetching Repos: ${e.message}"
                )
                //Log.e("language error",e.toString())
            }
        }
    }

    data class ReposState(
        val loading: Boolean = false,
        val list: List<Repo> = emptyList(),
        val error: String? = null
    )

    data class LanguageState(
        var loading: Boolean = true,
        val list: List<Language> = emptyList(),
        val error: String? = null
    )
}