package com.loki.data

import com.loki.data.dto.CocktailResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {

    @GET("filter.php")
    suspend fun getCocktails(
        @Query("c") cocktail: String
    ): CocktailResponseDto
}