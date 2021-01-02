package com.imaginato.test.base

import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.imaginato.test.R
import com.imaginato.test.util.CommonUtils
import com.imaginato.test.util.DialogUtils
import com.imaginato.test.util.NetworkUtils
import com.imaginato.test.util.ProgressUtil


/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */

abstract class BaseActivity : AppCompatActivity(), ConnectionBridge {


    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun postDataBinding(binding: ViewDataBinding)

    /**
     *To initialize the activity components
     */
    protected abstract fun initializeComponent()


    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = DataBindingUtil.setContentView(this, getLayoutId()) as ViewDataBinding
        super.onCreate(savedInstanceState)
        postDataBinding(binding)
        initializeComponent()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun isNetworkAvailable(): Boolean {
        return NetworkUtils(this).isNetworkConnected()
    }

    fun hideKeyboard() {
        CommonUtils.hideKeyboard(this)
    }

    //----------------------------- [Start] ProgressDialog Utils ----------------------------------
    fun showProgressDialog() {
        ProgressUtil.showOldProgressDialog(this)
    }

    fun hideProgressDialog() {
        ProgressUtil.closeOldProgressDialog()
    }

    //----------------------------- [End] ProgressDialog Utils -------------------------------------


    //----------------------------- [Start] Alert Dialog Utils -------------------------------------

    fun showAlertDialog(
        title: String,
        message: String,
        posBtnClickListener: DialogInterface.OnClickListener
    ) {
        DialogUtils.alert(
            this,
            title,
            message,
            R.string.action_dialog_default,
            posBtnClickListener
        )
    }


}