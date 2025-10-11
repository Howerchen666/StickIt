package com.example.stickit.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onNavigateToHome: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var isRegisterMode by remember { mutableStateOf(false) }

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(lifecycle) {
        Log.d("LifecycleLogger", "LoginScreen lifecycle state: ${lifecycle.currentState}")
    }

    DisposableEffect(Unit) {
        Log.d("LifecycleLogger", "LoginScreen entered composition")
        onDispose {
            Log.d("LifecycleLogger", "LoginScreen left composition")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 60.dp)
        ) {
            Text("⟲", fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(8.dp))
            Text("StickIt", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }

        // Email field
        Text("Email", fontSize = 14.sp, modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            placeholder = { Text("Value", color = Color.Gray) },
            colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray)
        )

        // Password field
        Text("Password", fontSize = 14.sp, modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            placeholder = { Text("Value", color = Color.Gray) },
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray)
        )

        // Checkbox
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                colors = CheckboxDefaults.colors(checkedColor = Color.Black)
            )
            Text("I agree to the terms and conditions", fontSize = 14.sp)
        }

        // Error message
        errorMessage?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(bottom = 16.dp))
        }

        // Toggle between Login and Register
        TextButton(onClick = { isRegisterMode = !isRegisterMode }) {
            Text(if (isRegisterMode) "Already have an account? Login" else "New user? Register")
        }

        // Auth button
        Button(
            onClick = {
                isLoading = true
                if (isRegisterMode) {
                    viewModel.register(email, password) { success ->
                        isLoading = false
                        if (success) {
                            Log.d("Auth", "Registration successful")
                            onNavigateToHome()
                        } else {
                            Log.d("Auth", "Registration failed")
                            errorMessage = "Registration failed. Try a different email."
                        }
                    }
                } else {
                    viewModel.signIn(email, password) { success ->
                        isLoading = false
                        if (success) {
                            Log.d("Auth", "Login successful")
                            onNavigateToHome()
                        } else {
                            Log.d("Auth", "Login failed")
                            errorMessage = "Invalid email or password"
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = MaterialTheme.shapes.small,
            enabled = !isLoading
        ) {
            Text(if (isRegisterMode) "Register" else "Login", color = Color.White, fontSize = 16.sp)
        }
    }
}