package com.imaginato.test.di

import com.imaginato.test.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
val viewmodelModule = module {
/*Login ViewModel*/
    viewModel {
        LoginViewModel(get()/*resource dependency*/)
    }
}