package com.example.stickit.screens

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

@Composable
fun LoginScreen(onNavigateToHome: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

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
            Text(
                text = "⟲",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "StickIt",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Email field
        Text(
            text = "Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            fontSize = 14.sp
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            placeholder = { Text("Value", color = Color.Gray) },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray
            )
        )

        // Password field
        Text(
            text = "Password",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            fontSize = 14.sp
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            placeholder = { Text("Value", color = Color.Gray) },
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray
            )
        )

        // Checkbox
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Black
                )
            )
            Text(
                text = "I agree to the terms and conditions",
                fontSize = 14.sp
            )
        }

        // Register button
        Button(
            onClick = { onNavigateToHome() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = "Register",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

