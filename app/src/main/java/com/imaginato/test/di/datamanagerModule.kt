package com.imaginato.test.di

import android.content.Context
import android.content.SharedPreferences
import com.imaginato.test.data.model.local.pref.PreferenceManager
import com.imaginato.test.util.Constant
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

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
}

private fun provideSharePreferences(context: Context) =
    context.getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE)