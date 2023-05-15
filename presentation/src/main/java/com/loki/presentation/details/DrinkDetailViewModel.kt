package com.loki.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.core.Resource
import com.loki.domain.repository.CocktailDetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DrinkDetailViewModel(
    private val repository: CocktailDetailRepository
): ViewModel() {

    private val _drinkDetail = MutableStateFlow(DrinkDetailState())
    val drinkDetail = _drinkDetail.asStateFlow()

     fun getDrinkDetail(id: String) {

        viewModelScope.launch {

            repository.getCocktailDetail(id).collect { result ->

                when(result) {
                    is Resource.Loading -> {
                        _drinkDetail.value = DrinkDetailState(
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {

                        _drinkDetail.value = DrinkDetailState(
                            drinkDetail = result.data!![0]
                        )
                    }

                    is Resource.Error -> {
                        _drinkDetail.value = DrinkDetailState(
                            errorMessage = result.message ?: "Something went wrong"
                        )
                    }
                }
            }
        }
    }
}