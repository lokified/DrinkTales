package com.loki.presentation.details

import com.loki.domain.models.DrinkDetail

data class DrinkDetailState(
    val isLoading: Boolean = false,
    val drinkDetail: DrinkDetail? = null,
    val errorMessage: String = ""
)
