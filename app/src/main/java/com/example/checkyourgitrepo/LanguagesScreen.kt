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
    val viewState by languageViewModel.languageState

    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            viewState.error != null -> {
                Text(text = "Error occurred")
            }
            else -> {
                // Display Languages
                LanguagesScreen(languages = viewState.list)

            }
        }
    }
}

@Composable
fun LanguagesScreen(languages: List<Language>){
    Column(
        
    ) {
        Text(text = "repo name placeholder")
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ){
            items(languages){
                    language ->
                LanguageItem(language = language)
            }
        }
        
    }

}

@Composable
fun LanguageItem(language: Language){
    Text(text = language.toString())
}