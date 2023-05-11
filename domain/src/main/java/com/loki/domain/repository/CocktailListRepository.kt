package com.loki.domain.repository

import com.loki.core.Resource
import com.loki.domain.models.Drink
import kotlinx.coroutines.flow.Flow

interface CocktailListRepository {

    suspend fun getCocktailList(): Flow<Resource<List<Drink>>>
}