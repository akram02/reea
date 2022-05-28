package com.example.reea.di

import android.app.Application
import com.example.reea.utils.PreferenceUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Hilt {

    @Provides
    @Singleton
    fun providePreference(application: Application) = PreferenceUtils(application)
}