package com.loki.presentation.home

import com.loki.domain.models.Drink

data class HomeState(
    val isLoading: Boolean = false,
    val cocktails: List<Drink> = emptyList(),
    val errorMessage: String = ""
)
