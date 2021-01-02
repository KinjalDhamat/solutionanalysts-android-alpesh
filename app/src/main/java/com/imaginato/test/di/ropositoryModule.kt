package com.imaginato.test.di

import com.imaginato.test.data.model.repo.UserRepository
import org.koin.dsl.module

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */

val repositoryModule = module {
    single { UserRepository(get()/*pass APIHelper dependency for API operation*/, get()/*pass userDao dependency for db operation*/) }
}