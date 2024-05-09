package com.example.checkyourgitrepo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


@Composable
fun ReposScreen(navController: NavController){

    val repoViewModel: MainViewModel = viewModel()
    val repoState by repoViewModel.reposState

    Column(

    ) {
        OutlinedTextField(
            value = repoViewModel.username,
            label = { Text(text = "Github username")},
            onValueChange = { repoViewModel.onUsernameChanged(it) },
            placeholder = { Text(text = "Search Github user")},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    repoViewModel.fetchRepos()
                    repoViewModel.searchedUsername = repoViewModel.username
                }
            ),
            trailingIcon = {
                if (repoViewModel.username.isNotEmpty()) {
                    IconButton(
                        onClick = { repoViewModel.username = "" }
                    ) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                    }
                }
            }
        )

        Box(modifier = Modifier.fillMaxSize()){
            when{
                repoState.loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                repoState.error != null -> {
                    Text(text = "Error occurred")
                }
                else -> {
                    // Display Repos
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ){
                        items(repoState.list){
                                repo ->
                            RepoItem(repo = repo, navController, username = repoViewModel.searchedUsername)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun RepoItem(repo: Repo, navController: NavController, username: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(text = repo.name)
                if(repo.language == "No languages listed") {
                    Text(text = repo.language)
                } else {
                    Text(text = "Mostly: ${repo.language}")
                }
            }

            IconButton(onClick = {
                //TODO no languages exception
                navController.navigate(Screen.Languages.route + "/${repo.name}/$username")
            }) {
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "To repo")
            }
        }
    }
}



@Preview
@Composable
fun Preview(){
    val previewRepoList: List<Repo> = listOf(Repo("name1", "language1"), Repo("name2", "language2"))
    //ReposScreen(previewRepoList)
}