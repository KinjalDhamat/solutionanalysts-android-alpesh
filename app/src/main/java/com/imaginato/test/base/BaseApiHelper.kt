package com.imaginato.test.base

import com.imaginato.test.data.model.remote.BaseResult
import com.imaginato.test.util.Constant
import retrofit2.Response
import java.net.ConnectException

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
open class BaseApiHelper {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                return if (response.body() != null) {
                    Resource.Success(response.body()!!, response.headers())
                } else {
                    val baseError = BaseResult()
                    baseError.errorCode = Constant.SOMETHING_WRONG_ERROR_STATUS
                    baseError.errorMessage = Constant.SOMETHING_WRONG_ERROR
                    Resource.Error(baseError)
                }
            } else {
                if (response.errorBody() != null) {
                    val baseError = BaseResult()
                    baseError.errorCode = Constant.SOMETHING_WRONG_ERROR_STATUS
                    baseError.errorMessage = Constant.SOMETHING_WRONG_ERROR
                    return Resource.Error(baseError)
                }

            }
        } catch (error: Exception) {
            return when (error) {
                is ConnectException -> {
                    val baseError = BaseResult()
                    baseError.errorCode = Constant.TIME_OUT_CONNECTION_STATUS
                    baseError.errorMessage = Constant.TIME_OUT_CONNECTION
                    Resource.Error(baseError)
                }

                else -> {
                    val baseError = BaseResult()
                    baseError.errorCode = Constant.SOMETHING_WRONG_ERROR_STATUS
                    baseError.errorMessage = Constant.SOMETHING_WRONG_ERROR
                    Resource.Error(baseError)
                }
            }

        }
        val baseError = BaseResult()
        baseError.errorCode = Constant.SOMETHING_WRONG_ERROR_STATUS
        baseError.errorMessage = Constant.SOMETHING_WRONG_ERROR
        return Resource.Error(baseError)
    }
}