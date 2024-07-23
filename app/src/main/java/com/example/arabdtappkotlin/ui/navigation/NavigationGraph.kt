package com.example.arabdtappkotlin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arabdtappkotlin.App
import com.example.arabdtappkotlin.data.repositories.AuthRepositoryImpl
import com.example.arabdtappkotlin.ui.view.screens.HomeScreen
import com.example.arabdtappkotlin.ui.view.screens.LoginScreen
import com.example.arabdtappkotlin.ui.view.screens.OnboardingScreen
import com.example.arabdtappkotlin.ui.view.screens.SplashScreen
import com.example.arabdtappkotlin.viewModel.UserViewModel

@Composable
fun AppNavigationGraph() {
    val viewModel =
        UserViewModel(
            authRepository = AuthRepositoryImpl(
                apiService = App.retrofit.authApiService,
                appRoomDatabase = App.appDatabase
            )
        )
    val navController = rememberNavController()
    NavHost(navController, startDestination = Routes.SPLASH_SCREEN_KEY) {
        composable(Routes.SPLASH_SCREEN_KEY) { SplashScreen(navController) }
        composable(Routes.ONBOARDING_SCREEN_KEY) { OnboardingScreen(navController) }
        composable(Routes.LOGIN_SCREEN_KEY) {
            LoginScreen(
                navController,
                userViewModel = viewModel
            )
        }
        composable(Routes.HOME_SCREEN_KEY) { HomeScreen() }
    }
}