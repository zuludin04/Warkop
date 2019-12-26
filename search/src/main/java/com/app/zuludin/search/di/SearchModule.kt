package com.app.zuludin.search.di

import com.app.zuludin.search.GetSearchRestaurantUseCase
import com.app.zuludin.search.SearchRestaurantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    factory { GetSearchRestaurantUseCase(get()) }
    viewModel { SearchRestaurantViewModel(get(), get()) }
}