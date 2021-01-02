package com.imaginato.test.base

import com.imaginato.test.data.model.remote.BaseResult
import okhttp3.Headers


sealed class Resource<out T> {
    class Success<out T>(val data: T, val header: Headers) : Resource<T>()
    class Error<out T>(val error: BaseResult) : Resource<T>()
    class Loading<out T>(val loading: EnumLoading) : Resource<T>()
}