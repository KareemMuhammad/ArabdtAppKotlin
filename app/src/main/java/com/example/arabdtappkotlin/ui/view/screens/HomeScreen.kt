package com.example.arabdtappkotlin.ui.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.arabdtappkotlin.data.models.UserSavedData
import com.example.arabdtappkotlin.viewModel.UserViewModel

@Composable
fun HomeScreen(navController: NavController,userViewModel: UserViewModel) {
    val userSavedData by userViewModel.savedUser.collectAsState()
//    LaunchedEffect(Unit) {
//
//    }

    Scaffold(
        modifier = Modifier.padding(16.dp),
        topBar = {

        }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(text = "Home Screen")
        }
    }
}

