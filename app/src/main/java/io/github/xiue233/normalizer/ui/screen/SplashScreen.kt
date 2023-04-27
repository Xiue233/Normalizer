package io.github.xiue233.normalizer.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.xiue233.normalizer.ui.NavigateHandler
import io.github.xiue233.normalizer.ui.navigation.Destinations
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateHandler: NavigateHandler
) {
    var visible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        delay(500)
        visible = true
        delay(500)
        navigateHandler(Destinations.MAIN) {
            popUpTo("splash") {
                inclusive = true
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color.Cyan, Color.Blue, Color.White
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        val density = LocalDensity.current
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(initialAlpha = 0f)
            ) {
                Image(
                    imageVector = Icons.Filled.Face, contentDescription = "logo",
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(100.dp)
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            AnimatedVisibility(
                visible = visible,
                enter = slideInVertically {
                    // Slide in from 40 dp from the top.
                    with(density) { -40.dp.roundToPx() }
                } + expandVertically(
                    // Expand from the top.
                    expandFrom = Alignment.Top
                ) + fadeIn(
                    // Fade in with the initial alpha of 0.3f.
                    initialAlpha = 0f
                ),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                Text(
                    text = "Normalizer",
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 22.sp
                    )
                )
            }
        }
    }
}