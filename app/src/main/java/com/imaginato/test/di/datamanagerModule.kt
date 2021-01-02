package com.imaginato.test.di

import android.content.Context
import android.content.SharedPreferences
import com.imaginato.test.BuildConfig
import com.imaginato.test.data.model.local.pref.PreferenceManager
import com.imaginato.test.util.Constant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
val datamanagerModule = module {


    /**
     * Provides shared preference instance and
     * its manager
     **/
    single<SharedPreferences> {
        provideSharePreferences(
            androidContext()
        )
    }
    single { PreferenceManager(get()/*Pass shared preference instance*/) }

    /**Provides OkHttpClient and its related
     * interceptor
     **/
    single(named(Constant.INTERCEPTOR_LOGGING)) { provideLoggingInterceptor() }
    single(named(Constant.INTERCEPTOR_OK_HTTP)) {
        provideHeaderInterceptor(
            get()
        )
    }
    single {
        provideOkHttpClient(
            get(named(Constant.INTERCEPTOR_LOGGING)),
            get(named(Constant.INTERCEPTOR_OK_HTTP))
        )
    }
}

//preference dependency
private fun provideSharePreferences(context: Context) =
    context.getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE)

//network dependencies
private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
        Timber.tag(Constant.TAG_OK_HTTP).d(message)
    }).apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

private fun provideHeaderInterceptor(prefManger: PreferenceManager): Interceptor =
    Interceptor { chain ->
        val imeiNumber = prefManger.getIMEINumber()
        val imsiNumber = prefManger.getIMSINumber()
        chain.proceed(
            chain.request().newBuilder().addHeader(Constant.HEADER_IMEI, imeiNumber)
                .addHeader(Constant.HEADER_IMSI, imsiNumber).build()
        )
    }

private fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    headerInterceptor: Interceptor
) = if (BuildConfig.DEBUG) {
    OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(headerInterceptor).build()
} else {
    OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(headerInterceptor).build()
}