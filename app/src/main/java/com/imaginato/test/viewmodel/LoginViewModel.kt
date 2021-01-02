package com.imaginato.test.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.imaginato.test.data.LoginForm

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
class LoginViewModel : ViewModel() {
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
            //bind error
            return false
        } else if (loginFields.password.isEmpty()) {
            errPassword.set("")
            //bind error
            return false
        }
        resetErrorFields()
        return true
    }
}