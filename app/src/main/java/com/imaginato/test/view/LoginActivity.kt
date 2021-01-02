package com.imaginato.test.view

import androidx.databinding.ViewDataBinding
import com.imaginato.test.R
import com.imaginato.test.base.BaseActivity
import com.imaginato.test.databinding.ActivityLoginBinding

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
class LoginActivity : BaseActivity() {

    lateinit var mActivityBinding: ActivityLoginBinding

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun postDataBinding(binding: ViewDataBinding) {
        mActivityBinding = binding as ActivityLoginBinding
    }

    override fun initializeComponent() {
    }
}