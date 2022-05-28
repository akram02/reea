package com.example.reea.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.DisplayMetrics
import java.util.*
import javax.inject.Inject

class LanguageUtils @Inject constructor(private val preference: PreferenceUtils) {

    fun setupLanguage(context: Context) {
        val myLocale = Locale(getLanguage())

        val res: Resources = context.resources
        val dm: DisplayMetrics = res.displayMetrics
        val conf: Configuration = res.configuration
        conf.setLocale(myLocale)
        res.updateConfiguration(conf, dm)
    }

    private fun getLanguage(): String {
        var language = preference.getValue("language")

        if (language == null)
            language = "en"
        return language
    }

    fun toggleLanguage(context: Context) {
        var language = getLanguage()
        language = if (language == "en") "bn" else "en"
        preference.putValue("language", language)
        setupLanguage(context)
    }
}