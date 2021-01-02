package com.imaginato.test.di

import com.imaginato.test.data.model.api.ApiService
import com.imaginato.test.util.Constant
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
val apiModule = module {
    /**
     * Provides Retrofit and api service
     * dependencies
     **/
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(Constant.BASE_URL).client(okHttpClient).addCallAdapterFactory(
        CoroutineCallAdapterFactory()
    ).addConverterFactory(GsonConverterFactory.create())
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)