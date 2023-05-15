package com.loki.presentation.details

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.loki.domain.models.DrinkDetail

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkDetailScreen(
    viewModel: DrinkDetailViewModel,
    id: String,
    dominantColor: String,
    overlayColor: String
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val color = MaterialTheme.colorScheme.primary

    val originalColor by remember { mutableStateOf(color) }

    val lifecycleObserver = LifecycleEventObserver { _, event  ->

        if (event == Lifecycle.Event.ON_RESUME) {
            changeStatusBarColor(context, Color(dominantColor.toInt()))
        } else if (event == Lifecycle.Event.ON_PAUSE) {
            changeStatusBarColor(context, originalColor)
        }
    }

    lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

    DisposableEffect(key1 = Unit) {
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }

    viewModel.getDrinkDetail(id)

    val state = viewModel.drinkDetail.collectAsStateWithLifecycle()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DetailTopBar(imageColor = dominantColor)
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {

            if (state.value.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            if (state.value.errorMessage.isNotBlank()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = state.value.errorMessage)
                }
            }

            state.value.drinkDetail?.let { drink ->
                ImageSection(drink = drink)
                val ingredients = listOfNotNull(drink.strIngredient1, drink.strIngredient2, drink.strIngredient3, drink.strIngredient4)
                val measure = listOfNotNull(drink.strMeasure1, drink.strMeasure2, drink.strMeasure3, drink.strMeasure4)
                IngredientMeasureSection(
                    title = "Ingredients",
                    items = ingredients,
                    overlayColor = overlayColor.toInt(),
                    modifier = Modifier.padding(16.dp)
                )

                drink.strInstructions?.let { instruction ->
                    InstructionSection(
                        instructions = instruction,
                        overlayColor = overlayColor.toInt(),
                        modifier = Modifier.padding(16.dp)
                    )
                }

                IngredientMeasureSection(
                    title = "Measure",
                    items = measure,
                    overlayColor = overlayColor.toInt(),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

    }
}

@Composable
fun DetailTopBar(
    imageColor: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(imageColor.toInt()),
                        Color.Transparent
                    )
                )
            )
    )
}

@Composable
fun ImageSection(
    drink: DrinkDetail
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {

        AsyncImage(
            model = drink.strDrinkThumb,
            contentDescription = drink.strDrink,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = drink.strDrink,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun IngredientMeasureSection(
    modifier: Modifier = Modifier,
    title: String,
    items: List<String>,
    overlayColor: Int
) {


    Column(modifier = modifier) {
        Text(
            text = title,
            color = Color(overlayColor),
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic
        )

        Spacer(modifier = Modifier.height(8.dp))

        items.onEachIndexed { index, item ->

            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(text = (index + 1).toString())
                Text(text = " - ")
                Text(text = item)
            }
        }
    }
}

@Composable
fun InstructionSection(
    modifier: Modifier = Modifier,
    instructions: String,
    overlayColor: Int
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Instructions",
            color = Color(overlayColor),
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center
        )

        Text(
            text = instructions,
            color = Color(overlayColor),
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center
        )
    }
}

private fun changeStatusBarColor(context: Context, color: Color) {

    val window: Window = (context as Activity).window
    window.statusBarColor = color.toArgb()
}