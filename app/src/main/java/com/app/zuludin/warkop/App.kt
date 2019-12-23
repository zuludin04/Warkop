package com.app.zuludin.warkop

import android.app.Application
import com.app.zuludin.data.di.dataModule
import com.app.zuludin.data.di.repositoryModule
import com.app.zuludin.detail.di.cafeDetailModule
import com.app.zuludin.home.di.homeModule
import com.app.zuludin.list.di.cafeListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class App : Application() {
    override fun onCreate() {
        super.onCreate()
        configureDI()
    }

    open fun configureDI() = startKoin {
        androidContext(this@App)
        modules(listOf(dataModule, repositoryModule,
            homeModule, cafeListModule, cafeDetailModule))
    }
}