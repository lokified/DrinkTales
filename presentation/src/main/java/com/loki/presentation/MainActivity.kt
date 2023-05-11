package com.loki.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.loki.presentation.home.HomeViewModel
import com.loki.presentation.navigation.Navigation
import com.loki.presentation.ui.theme.CocktailTheme
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            CocktailTheme {

                val appState = rememberAppState()


                Navigation(appState = appState)
            }
        }
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    CocktailAppState(navController)
}
