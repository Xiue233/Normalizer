package io.github.xiue233.normalizer.ui.navigation

sealed class Destinations(val route: String) {
    object SPLASH : Destinations("splash")

    object MAIN : Destinations("main")
}