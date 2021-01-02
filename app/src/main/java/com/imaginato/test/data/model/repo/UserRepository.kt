package com.imaginato.test.data.model.repo

import com.imaginato.test.base.Resource
import com.imaginato.test.data.model.api.ApiHelper
import com.imaginato.test.data.model.local.db.User
import com.imaginato.test.data.model.local.db.UserDao
import com.imaginato.test.data.model.remote.BaseResult
import com.imaginato.test.data.model.remote.request.ReqUserLogin
import com.imaginato.test.data.model.remote.response.RUserData
import com.imaginato.test.util.Constant

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
class UserRepository constructor(private val apiHelper: ApiHelper, private val userDao: UserDao) {

    suspend fun userLogin(request: ReqUserLogin): Resource<RUserData> {
        val result = apiHelper.userLogin(request)
        if (result is Resource.Success) {
            if (result.data.user != null) {//check if user login succeed or not
                val xAcc = result.header.get(Constant.HEADER_KEY_X_ACC_TYPE)
                if (xAcc != null && xAcc != "") {

                    //store in user table
                    result.data.user.let { userData ->
                        val user = User(user_id = userData.userId, user_name = userData.userName, x_acc = xAcc)
                        userDao.insert(user)
                    }
                }
            } else {//if userdata not found somehow
                return Resource.Error(result.data as BaseResult)
            }
        }
        return result
    }
}