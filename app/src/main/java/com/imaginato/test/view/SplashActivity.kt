package com.imaginato.test.view

import android.app.ActivityOptions
import android.content.Intent
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
        }, 1000)
    }

    //naviage to login screen
    private fun navigateToLogin() {
        val intentLoginActivity = Intent(this@SplashActivity, LoginActivity::class.java)
        val options = ActivityOptions.makeCustomAnimation(this, R.anim.anim_enter_from_right, R.anim.anim_exit_to_left)
        startActivity(intentLoginActivity, options.toBundle())
        finish()
    }
}