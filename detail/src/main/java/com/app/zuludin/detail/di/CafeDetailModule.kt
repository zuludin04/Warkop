package com.app.zuludin.detail.di

import com.app.zuludin.detail.CafeDetailViewModel
import com.app.zuludin.detail.GetDetailCafeUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cafeDetailModule = module {
    factory { GetDetailCafeUseCase(get()) }
    viewModel { CafeDetailViewModel(get(), get()) }
}