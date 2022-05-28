package com.example.reea

import android.app.Application
import com.example.reea.Config.BASE_URL
import com.example.reea.Config.REQUEST_TIMEOUT
import com.example.reea.network.RestApi
import com.example.reea.utils.PreferenceUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class HiltDI {

    @Provides
    fun providePreference(application: Application) = PreferenceUtils(application)

    @Provides
    fun provideRetrofit(converterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()

    @Provides
    fun provideApi(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)

    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }


    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor?,
        application: Application
    ): OkHttpClient {
        val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
        val cache = Cache(cacheDir, 10 * 1024 * 1024)
        val httpClient: OkHttpClient.Builder = OkHttpClient().newBuilder()
            .cache(cache)
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        httpClient.addInterceptor(loggingInterceptor!!)
        httpClient.addInterceptor(Interceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("X-Requested-With", "XMLHttpRequest")
            val newRequest: Request = builder.build()
            chain.proceed(newRequest)
        })
        return httpClient.build()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { it ->
            Timber.i(it)
        }
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }
}