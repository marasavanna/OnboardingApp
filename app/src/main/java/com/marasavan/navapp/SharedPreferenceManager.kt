package com.marasavan.navapp

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceManager(val context: Context) {

    private var sharedPreferences: SharedPreferences? = null

    companion object {
        private const val FILE_NAME = "appPrefs"
        const val USERNAME = "username"
    }

    init {
        sharedPreferences = context.applicationContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    }

    fun putString(key: String, value: String) {
        val editor = sharedPreferences?.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    fun getString(key: String, defaultValue: String): String? {
        return sharedPreferences?.getString(key, defaultValue)
    }
}