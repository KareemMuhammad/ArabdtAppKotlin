package com.example.arabdtappkotlin.ui.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.arabdtappkotlin.ui.components.CustomAppBar
import com.example.arabdtappkotlin.ui.components.HorizontalSpace
import com.example.arabdtappkotlin.ui.components.VerticalSpace
import com.example.arabdtappkotlin.ui.navigation.Routes
import com.example.arabdtappkotlin.ui.view.widgets.AttendanceWidget
import com.example.arabdtappkotlin.viewModel.UserViewModel

@Composable
fun HomeScreen(navController: NavController, userViewModel: UserViewModel) {
    val userSavedData by userViewModel.savedUser.collectAsState()
//    LaunchedEffect(Unit) {
//
//    }

    Scaffold(topBar = {
        CustomAppBar(
            barHeight = 320,
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 24.dp, top = 8.dp, bottom = 16.dp),
                ) {
                    Column {
                        Row {
                            AsyncImage(
                                model = "${userSavedData?.image}",
                                contentDescription = "user profile image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape)
                            )
                            HorizontalSpace(width = 8)
                            Text(
                                text = "Hi\n${userSavedData?.employee}",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.surfaceVariant
                            )
                        }
                        VerticalSpace(height = 120)
                        Box(
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.surface.copy(0.2f),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(8.dp),
                        ) {
                            Text(
                                text = "You haven't checked in yet",
                                color = MaterialTheme.colorScheme.surface,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Button(
                            onClick = {
                                checkIn()
                            },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                        ) {
                            Text(text = "Check In", style = MaterialTheme.typography.titleMedium)
                        }
                        VerticalSpace(height = 16)
                    }

                }
            }
        )
    }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                Row {
                    Text(text = "Attendance")
                    Spacer(modifier = Modifier.weight(1f))
                    ClickableText(
                        text = AnnotatedString("View All"),
                        style = TextStyle(color = MaterialTheme.colorScheme.primary),
                        onClick = { navController.navigate(Routes.ALL_ATTENDANCE_SCREEN_KEY) }
                    )
                }
                VerticalSpace(height = 8)
                Surface(
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)
                ) {
                    AttendanceWidget()
                }
            }
        }
    }
}

fun checkIn() {
    TODO("Not yet implemented")
}

