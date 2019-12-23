package com.app.zuludin.list.di

import com.app.zuludin.list.CafeListViewModel
import com.app.zuludin.list.GetCafeByCityUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cafeListModule = module {
    factory { GetCafeByCityUseCase(get())}
    viewModel { CafeListViewModel(get(), get()) }
}