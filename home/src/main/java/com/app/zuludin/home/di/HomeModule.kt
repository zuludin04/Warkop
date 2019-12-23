package com.app.zuludin.home.di

import com.app.zuludin.home.GetHomeDataUseCase
import com.app.zuludin.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory { GetHomeDataUseCase(get()) }
    viewModel { HomeViewModel(get(), get()) }
}