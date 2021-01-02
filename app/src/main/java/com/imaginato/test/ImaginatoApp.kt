package com.imaginato.test

import com.imaginato.test.base.BaseApp
import com.imaginato.test.di.basicModule
import com.imaginato.test.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
class ImaginatoApp : BaseApp() {
    override fun onCreate() {
        super.onCreate()

        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@ImaginatoApp)
            modules(
                listOf(basicModule, viewmodelModule)
            )
        }
    }
}