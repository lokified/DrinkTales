package com.loki.domain.repository

import com.loki.core.Resource
import com.loki.domain.models.DrinkDetail
import kotlinx.coroutines.flow.Flow

interface CocktailDetailRepository {

    suspend fun getCocktailDetail(id: String): Flow<Resource<List<DrinkDetail>>>
}