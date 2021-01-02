package com.imaginato.test.view

import android.os.Handler
import android.os.Looper
import androidx.databinding.ViewDataBinding
import com.imaginato.test.R
import com.imaginato.test.base.BaseActivity
import com.imaginato.test.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {
    lateinit var mActivityBinding: ActivitySplashBinding

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun postDataBinding(binding: ViewDataBinding) {
        mActivityBinding = binding as ActivitySplashBinding
    }

    override fun initializeComponent() {
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToLogin()
        }, 3000)
    }

    //naviage to login screen
    private fun navigateToLogin() {

    }
}