package com.imaginato.test.util

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
object BindingAdapter {

    @BindingAdapter("bind:errorMessage")
    @JvmStatic
    fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
        errorMessage?.let {
            view.error = it
            view.isErrorEnabled = !errorMessage.isNullOrEmpty()
        }
    }
}