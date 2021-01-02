package com.imaginato.test.viewmodel

import android.content.res.Resources
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.imaginato.test.R
import com.imaginato.test.data.LoginForm

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
class LoginViewModel constructor(private val resources: Resources) : ViewModel() {
    var errUsername = ObservableField<String>()
    var errPassword = ObservableField<String>()

    val loginFields: LoginForm = LoginForm("", "")

    private fun resetErrorFields() {
        errUsername.set("")
        errPassword.set("")
    }

    private fun resetFields() {
        loginFields.username = ""
        loginFields.password = ""
    }

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

    fun login() {
        if (isValidForm()) {

        }
    }
}