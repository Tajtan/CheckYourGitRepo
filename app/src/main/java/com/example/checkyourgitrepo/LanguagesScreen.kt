package com.example.checkyourgitrepo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun LanguagesScreen(navController: NavController){
    val languageViewModel: MainViewModel = viewModel()
    val languageState by languageViewModel.languageState

    languageViewModel.fetchLanguages()

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
                    Text(text = "repo name placeholder")
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


@Composable
fun LanguageItem(language: Language){
    Text(text = language.toString())
}