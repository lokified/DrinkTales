package com.loki.data.repository

import com.loki.core.Resource
import com.loki.data.CocktailApi
import com.loki.data.mappers.toDrink
import com.loki.domain.models.Drink
import com.loki.domain.repository.CocktailListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CocktailRepositoryImpl(
    private val api: CocktailApi
): CocktailListRepository {

    override suspend fun getCocktailList(): Flow<Resource<List<Drink>>> = flow {
        try {
            emit(Resource.Loading(data = null))
            emit(Resource.Success(data = api.getCocktails("Cocktail").drinks.map { it.toDrink() }))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred", data = null))
        } catch (e: IOException) {
            emit(Resource.Error("check your internet connection", data = null))
        }
    }
}