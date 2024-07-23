package com.example.arabdtappkotlin.ui.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arabdtappkotlin.ui.components.CustomAppBar

@Preview
@Composable
fun HomeScreen() {
    //navController: NavController, userViewModel: UserViewModel
//    val userSavedData by userViewModel.savedUser.collectAsState()
//    LaunchedEffect(Unit) {
//
//    }

    Scaffold(
        topBar = {
            CustomAppBar(
                barHeight = 250,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = "Home",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
        }) { paddingValues ->
        Box (modifier = Modifier.padding(paddingValues)) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                Row {
                    Text(text = "Attendance")
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "See All")
                }
            }
        }
    }
}

