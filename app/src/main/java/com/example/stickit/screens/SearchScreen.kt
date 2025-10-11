package com.example.stickit.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.eventFlow

@Composable
fun SearchScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToPost: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToSearch: () -> Unit
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val lifecycleState = lifecycle.currentStateFlow.collectAsState()

    LaunchedEffect(lifecycle) {
        lifecycle.eventFlow.collect { event ->
            Log.d("LifecycleLogger", "SearchScreen lifecycle event: $event → targetState: ${event.targetState}")
        }
    }

    DisposableEffect(Unit) {
        Log.d("LifecycleLogger", "SearchScreen entered composition")

        onDispose {
            Log.d("LifecycleLogger", "SearchScreen left composition - On_Destroy")
        }
    }

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

