package com.example.akharinkhabar.di

import android.content.Context
import com.example.akharinkhabar.data.local.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDataBase =
        AppDataBase.getInstance(appContext)

    @Singleton
    @Provides
    fun provideLatestNewsDao(database: AppDataBase) = database.latestNewsDao()
}
