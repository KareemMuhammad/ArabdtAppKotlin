package com.example.arabdtappkotlin.ui.view.screens

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.arabdtappkotlin.R
import com.example.arabdtappkotlin.ui.navigation.Routes
import com.example.arabdtappkotlin.utils.PreferencesManager
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    val postNotificationPermission = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

    val preferencesManager = PreferencesManager(context)
    val isOnboarded = preferencesManager.getBoolean(PreferencesManager.ONBOARDING_KEY, false)
    val userToken = preferencesManager.getString(PreferencesManager.TOKEN_KEY, "")

    LaunchedEffect(key1 = Unit) {
        delay(2000)
        if (!postNotificationPermission.status.isGranted) {
            postNotificationPermission.launchPermissionRequest()
        }
        if (isOnboarded) {
            if (userToken.isNullOrEmpty()) {
                navigateToLoginScreen(navController)
            } else {
                navigateToMainScreen(navController)
            }
        } else {
            navigateToOnBoardingScreen(navController)
        }
    }

    Image(
        painter = painterResource(R.drawable.splash_bg),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}

private fun navigateToOnBoardingScreen(navController: NavController) {
    navController.navigate(Routes.ONBOARDING_SCREEN_KEY) {
        popUpTo(Routes.SPLASH_SCREEN_KEY) { inclusive = true }
    }
}

private fun navigateToMainScreen(navController: NavController) {
    navController.navigate(Routes.MAIN_SCREEN_KEY) {
        popUpTo(Routes.SPLASH_SCREEN_KEY) { inclusive = true }
    }
}

private fun navigateToLoginScreen(navController: NavController) {
    navController.navigate(Routes.LOGIN_SCREEN_KEY) {
        popUpTo(Routes.SPLASH_SCREEN_KEY) { inclusive = true }
    }
}
