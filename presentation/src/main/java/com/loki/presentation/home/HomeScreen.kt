package com.loki.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loki.presentation.common.AppTopBar
import com.loki.presentation.common.CocktailItem
import com.loki.presentation.navigation.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    openScreen: (String) -> Unit,
    viewModel: HomeViewModel
) {

    val drinks = viewModel.cocktailState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Cocktails"
            )
        }
    ) {
        
        
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            if (drinks.value.isLoading) {
                CircularProgressIndicator()
            }

            if (drinks.value.cocktails.isNotEmpty()) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(),
                    columns = GridCells.Fixed(2)
                ) {

                    items(drinks.value.cocktails) { drink ->

                        CocktailItem(
                            drink = drink,
                            modifier = Modifier.padding(8.dp),
                            viewModel = viewModel,
                            onItemClick = { dominantColor, overlayColor ->
                                openScreen(
                                    Screens.DetailScreen
                                        .navWithArgs(
                                            drinkId = drink.idDrink,
                                            dominantColor = dominantColor.toString(),
                                            overlayColor = overlayColor.toString()
                                        )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}