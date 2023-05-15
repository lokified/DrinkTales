package com.loki.data.repository

import com.loki.core.Resource
import com.loki.data.CocktailApi
import com.loki.data.mappers.toDrinkDetail
import com.loki.domain.models.DrinkDetail
import com.loki.domain.repository.CocktailDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CocktailDetailRepositoryImpl(
    private val api: CocktailApi
): CocktailDetailRepository {

    override suspend fun getCocktailDetail(id: String): Flow<Resource<List<DrinkDetail>>> = flow {

        try {
            emit(Resource.Loading(data = null))
            emit(Resource.Success(data = api.getCocktailDetail(id).drinks.map { it.toDrinkDetail() }))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred", data = null))
        } catch (e: IOException) {
            emit(Resource.Error("check your internet connection", data = null))
        }
    }
}