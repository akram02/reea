package com.example.reea.utils

import android.app.Application
import android.content.Context
import javax.inject.Inject

class PreferenceUtils @Inject constructor(context: Application) {

    private val sharedPreferences = context.getSharedPreferences("Cache", Context.MODE_PRIVATE)

    fun putValue(key: String?, value: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getValue(key: String?): String? = sharedPreferences.getString(key, null)

}