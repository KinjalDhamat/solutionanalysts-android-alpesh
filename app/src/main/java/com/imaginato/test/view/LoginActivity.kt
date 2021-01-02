package com.imaginato.test.view

import androidx.databinding.ViewDataBinding
import com.imaginato.test.R
import com.imaginato.test.base.BaseActivity
import com.imaginato.test.databinding.ActivityLoginBinding
import com.imaginato.test.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
class LoginActivity : BaseActivity() {

    lateinit var mActivityBinding: ActivityLoginBinding

    //provide from viewmodelModule.kt
    private val mViewModel by viewModel<LoginViewModel>()

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun postDataBinding(binding: ViewDataBinding) {
        mActivityBinding = binding as ActivityLoginBinding
        mActivityBinding.viewModel = mViewModel
    }

    override fun initializeComponent() {
        setupUI()
    }

    private fun setupUI() {
        btn_login.setOnClickListener {
            hideKeyboard()
            mViewModel.login()
        }
    }
}