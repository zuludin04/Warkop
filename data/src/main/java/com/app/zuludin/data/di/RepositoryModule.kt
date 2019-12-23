package com.app.zuludin.data.di

import com.app.zuludin.data.AppDispatchers
import com.app.zuludin.data.WarkopRepository
import com.app.zuludin.data.WarkopRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
    factory { WarkopRepositoryImpl(get()) as WarkopRepository }
}