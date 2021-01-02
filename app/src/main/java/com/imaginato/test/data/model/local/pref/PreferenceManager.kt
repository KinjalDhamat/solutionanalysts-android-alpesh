package com.imaginato.test.data.model.local.pref

import android.content.SharedPreferences
import com.google.gson.GsonBuilder

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
class PreferenceManager constructor(var sharedPreferences: SharedPreferences) : IPreferenceManager {
    companion object {
        const val KEY_IMEI_NUMBER = "pre.key.imei.number"
        const val KEY_IMSI_NUMBER = "pre.key.imsi.number"
    }

    override fun saveIMEINumber(imeiNumber: String) {
        sharedPreferences.edit().putString(KEY_IMEI_NUMBER, imeiNumber).apply()
    }

    override fun getIMEINumber(): String {
        return sharedPreferences.getString(KEY_IMEI_NUMBER, "") ?: ""
    }

    override fun saveIMSINumber(imsiNumber: String) {
        sharedPreferences.edit().putString(KEY_IMSI_NUMBER, imsiNumber).apply()
    }

    override fun getIMSINumber(): String {
        return sharedPreferences.getString(KEY_IMSI_NUMBER, "") ?: ""
    }

    /**
     * Saves object into the Preferences.
     *
     * @param `object` Object of model class (of type [T]) to save
     * @param key Key with which Shared preferences to
     **/
    fun <T> put(obj: T, key: String) {
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(obj)
        //Save that String in SharedPreferences
        sharedPreferences.edit().putString(key, jsonString).apply()
    }

    /**
     * Used to retrieve object from the Preferences.
     *
     * @param key Shared Preference key with which object was saved.
     **/
    inline fun <reified T> get(key: String): T? {
        //We read JSON String which was saved.
        val value = sharedPreferences.getString(key, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type “T” is used to cast.
        return GsonBuilder().create().fromJson(value, T::class.java)
    }
}