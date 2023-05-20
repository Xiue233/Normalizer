package io.github.xiue233.normalizer.ui.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

typealias NavigateHandler = (Destinations, NavOptionsBuilder.() -> Unit) -> Unit

fun NavHostController.navigate(
    destinations: Destinations,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(destinations.route, builder)
}
