package com.example.arabdtappkotlin.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.arabdtappkotlin.R
import com.example.arabdtappkotlin.model.LoginDataModel
import com.example.arabdtappkotlin.networks.RetrofitClient
import com.example.arabdtappkotlin.view.helpers.appTextFieldColors
import com.example.arabdtappkotlin.view.helpers.appTextFieldModifier
import com.example.arabdtappkotlin.utils.Constants
import com.example.arabdtappkotlin.utils.PreferencesManager
import com.example.arabdtappkotlin.viewModel.UiState
import com.example.arabdtappkotlin.viewModel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailExists by remember { mutableStateOf(false) }
    var isPasswordExists by remember { mutableStateOf(false) }
    val viewModel = UserViewModel(authApiService = RetrofitClient.authApiService)
    val isLoading by viewModel.isLoading.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    println("UserViewModel: ${isLoading}")

    LaunchedEffect(Unit) {
        val savedEmail = ""
        val savedPassword = ""
        email = savedEmail
        password = savedPassword
        isEmailExists = savedEmail.isNotEmpty()
        isPasswordExists = savedPassword.isNotEmpty()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(17.dp, 40.dp, 17.dp, 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.align(alignment = Alignment.Start),
            text = "Welcome", // Assuming localized function
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.align(alignment = Alignment.Start),
        ) {
            Text(
                text = "Hello There, please login first",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(R.drawable.ic_welcome),
                contentDescription = null,
                modifier = Modifier.size(26.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.appTextFieldModifier(),
            value = email,
            onValueChange = {
                email = it
                isEmailExists = it.trim().isNotEmpty()
            },
            label = { Text("Email") },
            placeholder = { Text("Enter Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = appTextFieldColors()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.appTextFieldModifier(),
            value = password,
            onValueChange = {
                password = it
                isPasswordExists = it.trim().isNotEmpty()
            },
            label = { Text("Password") },
            placeholder = { Text("Enter Here") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            colors = appTextFieldColors()
        )

        TextButton(
            modifier = Modifier.align(alignment = Alignment.End),
            onClick = { /* _navigateToForgetPasswordScreen() equivalent */ },
            content = {
                Text(
                    text = "Forget Password?",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        textDecoration = TextDecoration.Underline
                    )
                )
            }
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                var loginData: LoginDataModel?
                if (isEmailExists && isPasswordExists) {
                    coroutineScope.launch {
                        loginData =
                            async { viewModel.login(email = email, password = password) }.await()
                        if (loginData != null) {
                            PreferencesManager(context = context).saveString(
                                key = PreferencesManager.TOKEN_KEY,
                                loginData?.token ?: ""
                            )
                            navController.navigate(Constants.HOME_SCREEN_KEY)
                        }
                    }
                }
            },
            enabled = isEmailExists && isPasswordExists,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isEmailExists && isPasswordExists) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary
            )
        ) {
            Text(
                "Login",
                Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        if (isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }

    }

}
