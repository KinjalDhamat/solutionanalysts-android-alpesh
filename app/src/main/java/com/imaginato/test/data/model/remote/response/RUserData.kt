package com.imaginato.test.data.model.remote.response

import com.imaginato.test.data.model.remote.BaseResult

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
data class RUserData(val user: User? = null) : BaseResult() {
    data class User(val userId: Long, val userName: String)
}