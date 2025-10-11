package com.example.stickit.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.eventFlow

@Composable
fun EventDetailScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToPost: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToSearch: () -> Unit
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val lifecycleState = lifecycle.currentStateFlow.collectAsState()

    LaunchedEffect(lifecycle) {
        lifecycle.eventFlow.collect { event ->
            Log.d("LifecycleLogger", "EventScreen lifecycle event: $event → targetState: ${event.targetState}")
        }
    }

    DisposableEffect(Unit) {
        Log.d("LifecycleLogger", "EventScreen entered composition")

        onDispose {
            Log.d("LifecycleLogger", "EventScreen left composition - OnDestroy Equivalent")
        }
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape),
                    color = Color(0xFF4CAF50)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            Icons.Default.Code,
                            contentDescription = "Code",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(
                selectedTab = "Home",
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
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Category chip
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.LightGray, CircleShape)
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(text = "⚽", fontSize = 14.sp)
                Text(text = "Sports", fontSize = 14.sp)
            }

            // Title
            Text(
                text = "⛳ Campus Golf Club",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Description
            Text(
                text = "Looking to get outside, relax, and enjoy a round of golf with fellow students? The Campus Golf Club is hosting a casual 9-hole game this weekend. Beginners and experienced players are all welcome!",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Details with bullets
            Column(
                modifier = Modifier.padding(bottom = 12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "• Location: Wilson Road Golf Course (just 15 minutes from campus)",
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "• Tee Time: 3:00 pm sharp – please arrive 20 minutes early",
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "• Cost: \$10 per person (club rentals available at the course)",
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "• Maximum Participants: 16 (first come, first served)",
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }

            Text(
                text = "Bring water, sunscreen, and good vibes. We'll grab dinner together afterward at a nearby restaurant for those interested.",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Join button
            Button(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "Join",
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
                )
            }
        }
    }
}

