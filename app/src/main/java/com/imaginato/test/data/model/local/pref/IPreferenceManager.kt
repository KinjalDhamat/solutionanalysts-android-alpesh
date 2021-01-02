package com.imaginato.test.data.model.local.pref

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
interface IPreferenceManager {
    fun saveIMEINumber(deviceToken: String)

    fun getIMEINumber(): String

    fun saveIMSINumber(deviceToken: String)

    fun getIMSINumber(): String
}