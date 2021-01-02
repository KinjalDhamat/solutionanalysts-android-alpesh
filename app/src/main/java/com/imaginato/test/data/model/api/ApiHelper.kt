package com.imaginato.test.data.model.api

import com.imaginato.test.base.Resource
import com.imaginato.test.data.model.remote.request.ReqUserLogin
import com.imaginato.test.data.model.remote.response.RUserData

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
interface ApiHelper {
    suspend fun userLogin(request: ReqUserLogin): Resource<RUserData>
}