package com.imaginato.test.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.databinding.ViewDataBinding
import com.imaginato.test.R
import com.imaginato.test.base.BaseActivity
import com.imaginato.test.data.model.local.pref.PreferenceManager
import com.imaginato.test.databinding.ActivitySplashBinding
import com.imaginato.test.util.Constant
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity() {
    lateinit var mActivityBinding: ActivitySplashBinding

    //provide from datamanagerModule.kt

    private val mPreferenceManger by inject<PreferenceManager>()

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun postDataBinding(binding: ViewDataBinding) {
        mActivityBinding = binding as ActivitySplashBinding
    }

    override fun initializeComponent() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (mPreferenceManger.getIMEINumber() != "" && mPreferenceManger.getIMSINumber() != "") navigateToLogin()
            else getImeiAndImsiNumber()
        }, 1000)
    }

    /*
    * from andoroid 10 onwards because of security reason not able to get IMEI number
    * */
    private fun getImeiAndImsiNumber() {
        if (mPreferenceManger.getIMEINumber() == "")
            mPreferenceManger.saveIMEINumber(Constant.IMEI_NUMBER)

        if (mPreferenceManger.getIMSINumber() == "")
            mPreferenceManger.saveIMSINumber(Constant.IMSI_NUMBER)

        navigateToLogin()
    }

    //naviage to login screen
    private fun navigateToLogin() {
        val intentLoginActivity = Intent(this@SplashActivity, LoginActivity::class.java)
        val options = ActivityOptions.makeCustomAnimation(this, R.anim.anim_enter_from_right, R.anim.anim_exit_to_left)
        startActivity(intentLoginActivity, options.toBundle())
        finish()
    }
}