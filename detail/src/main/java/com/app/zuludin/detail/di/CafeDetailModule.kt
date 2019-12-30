package com.app.zuludin.detail.di

import com.app.zuludin.detail.CafeDetailViewModel
import com.app.zuludin.detail.GetDetailCafeUseCase
import com.app.zuludin.detail.GetRestaurantReviewUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cafeDetailModule = module {
    factory { GetDetailCafeUseCase(get()) }
    factory { GetRestaurantReviewUseCase(get()) }
    viewModel { CafeDetailViewModel(get(), get(), get()) }
}