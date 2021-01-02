package com.imaginato.test.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.imaginato.test.BR

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
class LoginForm constructor(private var _username: String, private var _password: String) : BaseObservable() {

    var username: String
        @Bindable get() = _username
        set(value) {
            _username = value
            notifyPropertyChanged(BR.username)
        }

    var password: String
        @Bindable get() = _password
        set(value) {
            _password = value
            notifyPropertyChanged(BR.password)
        }
}