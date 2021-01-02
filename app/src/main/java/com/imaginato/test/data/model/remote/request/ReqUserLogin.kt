package com.imaginato.test.data.model.remote.request

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */

@Keep
data class ReqUserLogin(
    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("username")
    val username: String
)