package com.example.akharinkhabar.data.api

import com.example.akharinkhabar.data.model.LatestNew
import javax.inject.Inject

/**
 * Created by Moha on 2022-05-22.
 */

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun getAllNews(): List<LatestNew> = apiService.getAllNews()
}