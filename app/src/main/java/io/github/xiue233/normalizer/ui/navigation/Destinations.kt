package io.github.xiue233.normalizer.ui.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

sealed class Destinations(val route: String) {
    companion object {
        fun NavHostController.navigate(
            destinations: Destinations,
            builder: NavOptionsBuilder.() -> Unit = {}
        ) {
            navigate(destinations.route, builder)
        }
    }

    object SPLASH : Destinations("splash")

    object MAIN : Destinations("main")
}