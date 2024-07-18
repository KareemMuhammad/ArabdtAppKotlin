package com.example.arabdtappkotlin.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.arabdtappkotlin.R
import com.example.arabdtappkotlin.utils.Constants
import com.example.arabdtappkotlin.utils.PreferencesManager
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    val preferencesManager = PreferencesManager(context)
    val isOnboarded = preferencesManager.getBoolean(PreferencesManager.ONBOARDING_KEY, false)
    Image(
        painter = painterResource(R.drawable.splash_bg),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    LaunchedEffect(key1 = Unit) {
        delay(2000)
        if (isOnboarded) {
            navController.navigate(Constants.LOGIN_SCREEN_KEY) {
                popUpTo(Constants.SPLASH_SCREEN_KEY) { inclusive = true }
            }
        } else {
            navController.navigate(Constants.ONBOARDING_SCREEN_KEY) {
                popUpTo(Constants.SPLASH_SCREEN_KEY) { inclusive = true }
            }
        }
    }
}
