package com.example.akharinkhabar.data.api

import com.example.akharinkhabar.data.model.LatestNew
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Moha on 2022-05-22.
 */

interface ApiService {


    @GET("v1/list.php")
    suspend fun getAllNews(): List<LatestNew>

}