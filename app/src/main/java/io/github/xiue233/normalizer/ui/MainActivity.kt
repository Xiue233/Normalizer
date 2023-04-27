package io.github.xiue233.normalizer.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.xiue233.normalizer.ui.navigation.Destinations
import io.github.xiue233.normalizer.ui.navigation.Destinations.Companion.navigate
import io.github.xiue233.normalizer.ui.screen.SplashScreen


typealias NavigateHandler = (Destinations, NavOptionsBuilder.() -> Unit) -> Unit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navHostController: NavHostController;

    val navigateHandler: NavigateHandler = { destinations, builder ->
        navHostController.navigate(destinations, builder)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        val option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        val vis = window.decorView.systemUiVisibility
        window.decorView.systemUiVisibility = option or vis
        window.statusBarColor = Color.TRANSPARENT
        setContent {
            navHostController = rememberNavController();
            NavHost(navController = navHostController, startDestination = "splash") {
                composable(Destinations.SPLASH.route) {
                    SplashScreen(navigateHandler)
                }

                composable(Destinations.MAIN.route) {
                    Text("Main Page")
                }
            }
        }
    }
}