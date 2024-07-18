package com.example.arabdtappkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arabdtappkotlin.view.HomeScreen
import com.example.arabdtappkotlin.ui.theme.AppTheme
import com.example.arabdtappkotlin.view.LoginScreen
import com.example.arabdtappkotlin.view.OnboardingScreen
import com.example.arabdtappkotlin.view.SplashScreen
import com.example.arabdtappkotlin.utils.Constants

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = Constants.SPLASH_SCREEN_KEY) {
        composable(Constants.SPLASH_SCREEN_KEY) { SplashScreen(navController) }
        composable(Constants.ONBOARDING_SCREEN_KEY) { OnboardingScreen(navController) }
        composable(Constants.LOGIN_SCREEN_KEY) { LoginScreen(navController) }
        composable(Constants.HOME_SCREEN_KEY) { HomeScreen(navController) }
    }
}