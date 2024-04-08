package com.example.checkyourgitrepo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ReposScreen(modifier: Modifier = Modifier){
    val repoViewModel: MainViewModel = viewModel()
    val viewState by repoViewModel.reposState

    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            viewState.error != null -> {
                Text(text = "Error occured")
            }
            else -> {
                // Display Repos
                ReposScreen(repos = viewState.list)
            }
        }
    }
}



@Composable
fun ReposScreen(repos: List<Repos>){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        items(repos){
            repo ->
            RepoItem(repo = repo)
        }

    }
}


@Composable
fun RepoItem(repo: Repos){
    Column {
        Text(text = repo.name)
        Text(text = repo.language)
    }
}