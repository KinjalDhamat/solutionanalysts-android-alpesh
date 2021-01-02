package com.imaginato.test.viewmodel

import android.content.res.Resources
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaginato.test.R
import com.imaginato.test.base.EnumLoading
import com.imaginato.test.base.Resource
import com.imaginato.test.data.model.form.LoginForm
import com.imaginato.test.data.model.remote.BaseResult
import com.imaginato.test.data.model.remote.request.ReqUserLogin
import com.imaginato.test.data.model.remote.response.RUserData
import com.imaginato.test.data.model.repo.UserRepository
import com.imaginato.test.util.Constant
import com.imaginato.test.util.NetworkUtils
import kotlinx.coroutines.launch

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
class LoginViewModel constructor(private val resources: Resources, private val networkUtils: NetworkUtils, private val userRepository: UserRepository) : ViewModel() {

    /*notify username and password for error*/
    var errUsername = ObservableField<String>()
    var errPassword = ObservableField<String>()

    val loginFields: LoginForm = LoginForm("", "")

    /*notify login states to ui*/
    private val _userLogin = MutableLiveData<Resource<RUserData>>()
    val userLogin: LiveData<Resource<RUserData>>
        get() = _userLogin

    /*If form is validated reset previouse error states if any avaialable*/
    private fun resetErrorFields() {
        errUsername.set("")
        errPassword.set("")
    }

    private fun resetFields() {
        loginFields.username = ""
        loginFields.password = ""
    }

    /*Validate user login form*/
    private fun isValidForm(): Boolean {
        resetErrorFields()
        if (loginFields.username.trim().isEmpty()) {
            errUsername.set(resources.getString(R.string.error_empty_username))
            return false
        } else if (loginFields.password.isEmpty()) {
            errPassword.set("")
            errPassword.set(resources.getString(R.string.error_empty_password))
            return false
        }
        resetErrorFields()
        return true
    }

    /*Perform user login*/
    fun login() {
        if (isValidForm()) {
            viewModelScope.launch {

                //notify loading state
                _userLogin.postValue(Resource.Loading(EnumLoading.LOADING_ALL))

                if (networkUtils.isNetworkConnected()) {
                    userRepository.userLogin(ReqUserLogin(username = loginFields.username, password = loginFields.password)).let {

                        //notify success/failure state
                        _userLogin.postValue(it)
                        if (it is Resource.Success) {//reset login form field if user authenticated successfully
                            resetFields()
                        }
                    }
                } else {
                    //notify for not internet state
                    val baseError = BaseResult()
                    baseError.errorCode = Constant.NO_INTERNET_CONNECTION_STATUS
                    baseError.errorMessage = Constant.NO_INTERNET_CONNECTION
                    _userLogin.postValue(Resource.Error(baseError))
                }
            }
        }
    }
}