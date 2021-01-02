package com.imaginato.test.view

import android.content.DialogInterface
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.imaginato.test.R
import com.imaginato.test.base.BaseActivity
import com.imaginato.test.base.Resource
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
        setupObserver()
    }

    private fun setupUI() {
        btn_login.setOnClickListener {
            hideKeyboard()
            mViewModel.login()
        }
    }

    /*Observe user login states*/
    private fun setupObserver() {
        mViewModel.userLogin.observe(this, Observer { response ->
            response?.let {
                when (response) {
                    is Resource.Success -> {//call if case of API success
                        hideProgressDialog()

                        edt_login_username.requestFocus()
                        showAlertDialog(getString(R.string.app_name), response.data.errorMessage ?: getString(R.string.error_something_went_wrong),
                            DialogInterface.OnClickListener { p0, p1 ->
                                p0.dismiss()
                            })

                    }
                    is Resource.Error -> {//called in case of API failure
                        hideProgressDialog()
                        showAlertDialog(getString(R.string.app_name), response.error.errorMessage ?: getString(R.string.error_something_went_wrong),
                            DialogInterface.OnClickListener { p0, p1 ->
                                p0.dismiss()
                            })
                    }
                    is Resource.Loading -> {//handle progress
                        showProgressDialog()
                    }
                }
            }
        })
    }
}