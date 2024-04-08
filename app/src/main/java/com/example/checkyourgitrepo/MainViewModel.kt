package com.example.checkyourgitrepo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _reposState = mutableStateOf(ReposState())
    val reposState: State<ReposState> = _reposState

    init {
        fetchRepos()
    }


    private fun fetchRepos(){
        viewModelScope.launch {
            try {
                val response = repoService.getRepos()
                _reposState.value = _reposState.value.copy(
                    list = response.repos,
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

    data class ReposState(
        val loading: Boolean = true,
        val list: List<Repos> = emptyList(),
        val error: String? = null
    )

}