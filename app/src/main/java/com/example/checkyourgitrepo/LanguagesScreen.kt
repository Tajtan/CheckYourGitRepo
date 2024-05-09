package com.example.checkyourgitrepo

import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguagesScreen(navController: NavController, repo: String, username: String){
    val languageViewModel: MainViewModel = viewModel()
    val languageState by languageViewModel.languageState

    //Log.e("username", username)
    //Log.e("repo", repo)

    languageViewModel.fetchLanguages(repo, username)

    Box(modifier = Modifier.fillMaxSize()){
        when{
            languageState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            languageState.error != null -> {
                Text(text = "Error occurred")
            }
            else -> {
                // Display Languages
                Column(

                ) {
                    Text(text = repo)
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ){
                        items(languageState.list){
                                language ->
                            LanguageItem(language = language)
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Check Your Github repo")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go back to repos")
                    }
                }
            )
        }
    ) {
        paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)){
            when{
                languageState.loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                languageState.error != null -> {
                    Text(text = "Error occurred")
                }
                else -> {
                    // Display Languages
                    Column(

                    ) {
                        Text(text = repo)
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ){
                            items(languageState.list){
                                    language ->
                                LanguageItem(language = language)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun LanguageItem(language: Language) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = language.language)
            Text(text = "Bytes: ${language.bytes}")
        }
    }
}