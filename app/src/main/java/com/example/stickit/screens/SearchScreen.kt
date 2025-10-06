package com.example.stickit.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToPost: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToSearch: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedTab = "Search",
                onHomeClick = onNavigateToHome,
                onPostClick = onNavigateToPost,
                onProfileClick = onNavigateToProfile,
                onSearchClick = onNavigateToSearch
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Search",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

