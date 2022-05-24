package com.example.akharinkhabar.di

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.akharinkhabar.BuildConfig
import com.example.akharinkhabar.data.api.ApiHelper
import com.example.akharinkhabar.data.api.ApiHelperImpl
import com.example.akharinkhabar.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Moha on 2022-05-21.
 */

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    val responseStatus = MutableLiveData<Response>()


    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL


    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context) = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder().addNetworkInterceptor(Interceptor { chain ->
            val proceed = chain.proceed(request = chain.request())
            responseStatus.postValue(proceed)
            proceed
        }).addInterceptor { chain ->
            //send value for header requests
            var newBuilder = chain.request().newBuilder()

            // TODO: for add header http
            val request = newBuilder.build()
            chain.proceed(request)
        }.addInterceptor(CustomInterceptor()).addInterceptor(loggingInterceptor)
            .build()
    } else {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder().addNetworkInterceptor(Interceptor { chain ->
            val proceed = chain.proceed(request = chain.request())
            responseStatus.postValue(proceed)
            proceed
        }).addInterceptor { chain ->
            //send value for header requests
            var newBuilder = chain.request().newBuilder()
            // TODO: for add header http

            val request = newBuilder.build()
            chain.proceed(request)
        }.addInterceptor(CustomInterceptor()).addInterceptor(loggingInterceptor)
            .build()
    }

    class CustomInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder()
                .build()
            return chain.proceed(request)
        }
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)//base url in build config
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper


}