package com.example.arabdtappkotlin.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arabdtappkotlin.App
import com.example.arabdtappkotlin.data.repositories.AuthRepositoryImpl
import com.example.arabdtappkotlin.ui.view.screens.AllAttendanceScreen
import com.example.arabdtappkotlin.ui.view.screens.HomeScreen
import com.example.arabdtappkotlin.ui.view.screens.LoginScreen
import com.example.arabdtappkotlin.ui.view.screens.MainScreen
import com.example.arabdtappkotlin.ui.view.screens.MoreScreen
import com.example.arabdtappkotlin.ui.view.screens.OnboardingScreen
import com.example.arabdtappkotlin.ui.view.screens.SplashScreen
import com.example.arabdtappkotlin.viewModel.UserViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppNavigationGraph() {
    val userViewModel =
        UserViewModel(
            authRepository = AuthRepositoryImpl(
                apiService = App.retrofitClient.authApiService,
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
                userViewModel = userViewModel
            )
        }
        composable(Routes.MAIN_SCREEN_KEY) { MainScreen(navController,userViewModel) }
        composable(Routes.HOME_SCREEN_KEY) { HomeScreen(navController,userViewModel) }
        composable(Routes.MORE_SCREEN_KEY) { MoreScreen() }
        composable(Routes.ALL_ATTENDANCE_SCREEN_KEY) { AllAttendanceScreen(navController) }

    }
}