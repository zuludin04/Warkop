package com.app.zuludin.data.di

import com.app.zuludin.data.source.WarkopRemoteSource
import com.app.zuludin.data.source.api.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    single { client() }

    single {
        Retrofit.Builder()
            .baseUrl("https://developers.zomato.com/api/v2.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }

    factory { WarkopRemoteSource(get()) }
}

fun client(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.readTimeout(5 * 60, TimeUnit.SECONDS)
    return client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.header("user-key", "cbeab64a37c7a90fa6706e19ac9c69f2")
        requestBuilder.header("Accept", "application/json")
        val request = requestBuilder.method(original.method(), original.body()).build()
        return@addInterceptor it.proceed(request)
    }.build()
}