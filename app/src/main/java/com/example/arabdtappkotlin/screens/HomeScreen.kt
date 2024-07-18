package com.example.arabdtappkotlin.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.padding(16.dp),
        topBar = {

        }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(text = "Home Screen")
        }
    }
}