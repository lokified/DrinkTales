package com.loki.presentation.di

import com.loki.presentation.details.DrinkDetailViewModel
import com.loki.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        DrinkDetailViewModel(get())
    }
}