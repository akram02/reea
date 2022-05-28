package com.example.reea

import com.example.reea.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun setLayout() = R.layout.activity_main
}