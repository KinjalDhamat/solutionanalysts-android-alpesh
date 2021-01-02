package com.imaginato.test.base

import android.app.Application
import com.imaginato.test.BuildConfig
import timber.log.Timber

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initializeTimber()
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        if (instance != null) {
            instance = null
        }
    }

    companion object {
        var instance: BaseApp? = null
    }

}