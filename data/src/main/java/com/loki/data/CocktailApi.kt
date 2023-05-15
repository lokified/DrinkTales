package com.loki.data

import com.loki.core.Constants.COCKTAILS
import com.loki.core.Constants.COCKTAIL_DETAIL
import com.loki.data.dto.CocktailDetailDto
import com.loki.data.dto.CocktailResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {

    @GET(COCKTAILS)
    suspend fun getCocktails(
        @Query("c") cocktail: String
    ): CocktailResponseDto

    @GET(COCKTAIL_DETAIL)
    suspend fun getCocktailDetail(
        @Query("i") id: String
    ): CocktailDetailDto
}