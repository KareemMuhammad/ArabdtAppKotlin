package com.example.arabdtappkotlin.ui.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.arabdtappkotlin.ui.components.CustomAppBar

@Composable
fun AllAttendanceScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CustomAppBar(
                barHeight = 85,
                title = "All Attendance",
                withBackButton = true,
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

        }

    }
}