package com.loki.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.loki.domain.models.Drink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailItem(
    modifier: Modifier = Modifier,
    drink: Drink,
    onItemClick: () -> Unit
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = screenWidth / 2

    Card(
        modifier = modifier,
        onClick = onItemClick
    ) {
        Box(
            modifier = Modifier
                .size(height = 200.dp, width = cardWidth)
        ) {

            AsyncImage(
                model = drink.strDrinkThumb,
                contentDescription = drink.strDrink,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "favorite"
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .aspectRatio(1f)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = drink.strDrink,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}