package com.assessment.newsarticles.di

import com.assessment.newsarticles.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L

val retrofitModule = module {

    single { GsonBuilder().create() }
    single { retrofitHttpClient() }
    single { retrofitBuilder() }
}

private fun Scope.retrofitBuilder(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(get()))
        .client(get())
        .build()
}

private fun retrofitHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    }.build()
}