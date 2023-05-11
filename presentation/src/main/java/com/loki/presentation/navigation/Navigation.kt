package com.loki.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.loki.presentation.CocktailAppState
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
    }
}