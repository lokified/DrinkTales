package com.loki.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.loki.core.Constants.DOMINANT_COLOR
import com.loki.core.Constants.DRINK_ID
import com.loki.core.Constants.OVERLAY_COLOR
import com.loki.presentation.CocktailAppState
import com.loki.presentation.details.DrinkDetailScreen
import com.loki.presentation.details.DrinkDetailViewModel
import com.loki.presentation.home.HomeScreen
import com.loki.presentation.home.HomeViewModel
import org.koin.androidx.compose.get

@Composable
fun Navigation(
    appState: CocktailAppState
) {

    NavHost(
        navController = appState.navController,
        startDestination = Screens.HomeScreen.route
    ) {


        composable(route = Screens.HomeScreen.route) {

            val viewModel = get<HomeViewModel>()
            HomeScreen(
                openScreen = { appState.navigate(it) },
                viewModel = viewModel
            )
        }

        composable(
            route = Screens.DetailScreen.withDrinkId(),
            arguments = listOf(
                navArgument(DRINK_ID) {
                    type = NavType.StringType
                },
                navArgument(DOMINANT_COLOR) {
                    type = NavType.StringType
                },
                navArgument(OVERLAY_COLOR) {
                    type = NavType.StringType
                }
            )
        ) {

            val id = it.arguments?.getString(DRINK_ID)
            val dominantColor = it.arguments?.getString(DOMINANT_COLOR)
            val overlayColor = it.arguments?.getString(OVERLAY_COLOR)
            val viewModel = get<DrinkDetailViewModel>()

            id?.let { drinkId ->
                DrinkDetailScreen(
                    viewModel = viewModel,
                    id = drinkId,
                    dominantColor = dominantColor!!,
                    overlayColor = overlayColor!!
                )
            }
        }
    }
}