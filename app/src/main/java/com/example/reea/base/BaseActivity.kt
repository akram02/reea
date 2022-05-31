package com.example.reea.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.reea.utils.LanguageUtils
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var languageUtils: LanguageUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        languageUtils.setupLanguage(this)
    }
}