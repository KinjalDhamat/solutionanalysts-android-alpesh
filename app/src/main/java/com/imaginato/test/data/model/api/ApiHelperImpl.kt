package com.imaginato.test.data.model.api

import com.imaginato.test.base.BaseApiHelper
import com.imaginato.test.data.model.remote.request.ReqUserLogin

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
class ApiHelperImpl(private val apiService: ApiService/*get instance from apiModule.kt*/) : ApiHelper, BaseApiHelper() {
    override suspend fun userLogin(request: ReqUserLogin) = safeApiCall {
        apiService.userLogin(request)
    }
}