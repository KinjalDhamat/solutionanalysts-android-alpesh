package com.imaginato.test.util

import android.app.Dialog
import android.content.Context
import com.imaginato.test.R

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
object ProgressUtil {

    private var progressDialog: Dialog? = null

    fun showOldProgressDialog(context: Context) {
        if (progressDialog == null || !progressDialog!!.isShowing) {
            progressDialog = Dialog(context, R.style.Base_Theme_AppCompat_Dialog)
            progressDialog!!.setCancelable(false)
            progressDialog!!.setCanceledOnTouchOutside(false)
            progressDialog!!.show()
            progressDialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            progressDialog!!.setContentView(R.layout.layout_progress_dialog)
        }
    }

    fun closeOldProgressDialog() {
        try {
            if (progressDialog != null && progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
                progressDialog = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            progressDialog = null
        }
    }
}