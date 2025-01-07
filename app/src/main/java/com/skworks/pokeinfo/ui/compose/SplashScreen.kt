package com.skworks.pokeinfo.ui.compose

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.skworks.pokeinfo.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreenWithAnimation(navController: NavController) {
    val logoAlpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        logoAlpha.animateTo(1f, animationSpec = tween(durationMillis = 1500))
        delay(1500) // Keep the logo visible for a moment
        navController.navigate("Pokemons_List") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box( // Fill the whole screen with Box
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Fade-in animation for the logo
        Image(
            alignment = Alignment.Center,
            painter = painterResource(id = R.drawable.pngegg),
            contentDescription = "App Logo"

        )
    }
}