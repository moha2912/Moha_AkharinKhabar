package com.example.akharinkhabar.data.api

import com.example.akharinkhabar.data.model.LatestNew
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

/**
 * Created by Moha on 2022-05-22.
 */
interface ApiHelper {
    suspend fun getAllNews(): List<LatestNew>
}