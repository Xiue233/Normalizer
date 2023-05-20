package io.github.xiue233.normalizer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.xiue233.normalizer.R
import io.github.xiue233.normalizer.ui.navigation.Destinations
import io.github.xiue233.normalizer.ui.navigation.NavigateHandler
import io.github.xiue233.normalizer.ui.navigation.navigate
import io.github.xiue233.normalizer.ui.screen.MainScreen
import io.github.xiue233.normalizer.ui.screen.SplashScreen
import io.github.xiue233.normalizer.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController

    private val navigateHandler: NavigateHandler = { destinations, builder ->
        navHostController.navigate(destinations, builder)
    }

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Normalizer_Main)
        setContent {
            AppTheme {
                navHostController = rememberAnimatedNavController()
                Column {
                    AnimatedNavHost(
                        navController = navHostController,
                        startDestination = Destinations.SPLASH.route
                    ) {
                        composable(
                            Destinations.SPLASH.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() }
                        ) {
                            SplashScreen(navigateHandler)
                        }

                        composable(
                            Destinations.MAIN.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() }
                        ) {
                            MainScreen()
                        }
                    }
                }
            }
        }
    }
}