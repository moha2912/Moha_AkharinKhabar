package com.example.akharinkhabar.di

import android.content.Context
import com.example.akharinkhabar.data.LatestNewsDao
import com.example.akharinkhabar.data.api.ApiHelper
import com.example.akharinkhabar.data.local.AppDataBase
import com.example.akharinkhabar.data.repository.RepositoryMain
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

    @Singleton
    @Provides
    fun provideDefaultMainRepository(
        appDataBase: AppDataBase,
        apiHelper: ApiHelper
    ) = RepositoryMain(appDataBase , apiHelper)
}
