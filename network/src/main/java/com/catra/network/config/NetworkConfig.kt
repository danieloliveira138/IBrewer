package com.catra.network.config

import android.content.Context
import com.catra.network.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkConfig {
    fun<T> provideApi(baseUrl: String, clazz: Class<T>): T =
        Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .baseUrl(baseUrl)
            .client(provideOkHttpClient())
            .build()
            .create(clazz)

    private fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
            level = HttpLoggingInterceptor.Level.HEADERS
        }

        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private fun provideGson(): Gson =
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setLenient()
            .create()
}