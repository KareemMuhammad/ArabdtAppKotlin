package com.example.arabdtappkotlin.ui.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
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
import com.example.arabdtappkotlin.ui.components.appTextFieldColors
import com.example.arabdtappkotlin.ui.components.appTextFieldModifier
import com.example.arabdtappkotlin.ui.navigation.Routes
import com.example.arabdtappkotlin.utils.AppState
import com.example.arabdtappkotlin.viewModel.UserViewModel

@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailExists by remember { mutableStateOf(false) }
    var isPasswordExists by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    val state by userViewModel.state.collectAsState()
    val context = LocalContext.current

    println("state::: $state")
    LaunchedEffect(state) {
        when (state) {
            is AppState.Init -> Unit
            is AppState.Loading -> Unit
            is AppState.Success -> {
                navController.navigate(Routes.HOME_SCREEN_KEY)
            }

            is AppState.Error -> {
                showSnackbar = true
                snackbarMessage = (state as AppState.Error).message ?: "Error"
            }
        }
    }

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

        if (state is AppState.Loading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        } else {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (isEmailExists && isPasswordExists) {
                        userViewModel.login(
                            email = email,
                            password = password,
                            context
                        )
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
        }
        Box(modifier = Modifier.weight(1f))
            if (showSnackbar) {
            Snackbar(
                action = {
                    TextButton(onClick = { showSnackbar = false }) {
                        Text("OK")
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = (state as AppState.Error).message ?: "Error")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

    }

}
