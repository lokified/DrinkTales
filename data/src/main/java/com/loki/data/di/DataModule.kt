package com.loki.data.di

import com.loki.core.Constants.BASE_URL
import com.loki.data.CocktailApi
import com.loki.data.repository.CocktailRepositoryImpl
import com.loki.domain.repository.CocktailListRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {

    single {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CocktailApi::class.java)
    }

    single<CocktailListRepository> {
        CocktailRepositoryImpl(get())
    }

}