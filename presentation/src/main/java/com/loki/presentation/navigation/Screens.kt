package com.loki.presentation.navigation

import com.loki.core.Constants.DOMINANT_COLOR
import com.loki.core.Constants.DRINK_ID
import com.loki.core.Constants.OVERLAY_COLOR

sealed class Screens(val route: String) {
    object HomeScreen: Screens("home_screen")
    object DetailScreen: Screens("detail_screen")

    fun withDrinkId(): String {
        return "${DetailScreen.route}/{$DRINK_ID}/{$DOMINANT_COLOR}/{$OVERLAY_COLOR}"
    }

    fun navWithArgs(drinkId: String, dominantColor: String, overlayColor: String): String {
        return "${DetailScreen.route}/$drinkId/$dominantColor/$overlayColor"
    }
}
