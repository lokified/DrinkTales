package com.loki.presentation.home

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.loki.core.Resource
import com.loki.domain.repository.CocktailListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val cocktailRepository: CocktailListRepository
): ViewModel() {

    private val _cocktailState = MutableStateFlow(HomeState())
    val cocktailState = _cocktailState.asStateFlow()

    init {
        getCocktailList()
    }

    private fun getCocktailList() {

        viewModelScope.launch {

            cocktailRepository.getCocktailList().collect { result ->

                when(result) {
                    is Resource.Loading -> {
                        _cocktailState.value = HomeState(
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        _cocktailState.value = HomeState(
                            cocktails = result.data ?: emptyList()
                        )
                    }

                    is Resource.Error -> {
                        _cocktailState.value = HomeState(
                            errorMessage = result.message ?: "Something went wrong"
                        )
                    }
                }
            }
        }
    }

    fun getDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}