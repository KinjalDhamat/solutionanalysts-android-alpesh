package com.imaginato.test.data.model.repo

import com.imaginato.test.base.Resource
import com.imaginato.test.data.model.api.ApiHelper
import com.imaginato.test.data.model.remote.BaseResult
import com.imaginato.test.data.model.remote.request.ReqUserLogin
import com.imaginato.test.data.model.remote.response.RUserData

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
class UserRepository constructor(private val apiHelper: ApiHelper) {

    suspend fun userLogin(request: ReqUserLogin): Resource<RUserData> {
        val result = apiHelper.userLogin(request)
        if (result is Resource.Success) {
            if (result.data.user != null) {//check if user login succeed or not

            } else {//if userdata not found somehow
                return Resource.Error(result.data as BaseResult)
            }
        }
        return result
    }
}