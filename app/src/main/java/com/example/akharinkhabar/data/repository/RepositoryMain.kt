package com.example.akharinkhabar.data.repository

import android.util.Log
import com.example.akharinkhabar.data.model.db.LatestNewsItem
import com.example.akharinkhabar.data.api.ApiHelper
import com.example.akharinkhabar.data.local.AppDataBase
import com.example.akharinkhabar.data.model.ErrorModel
import com.example.akharinkhabar.data.model.db.RelationMain
import com.example.akharinkhabar.other.Event
import com.example.akharinkhabar.other.Resource
import com.example.akharinkhabar.other.ResponseHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

/**
 * Created by Moha on 2022-05-21.
 */

class RepositoryMain @Inject constructor(
    private val appDataBase: AppDataBase,
    private val apiHelper: ApiHelper
) {
    private val responseHandler = ResponseHandler()

    suspend fun getAllFromApiAndObserve(): Flow<Event<Resource<List<RelationMain>>>> = flow {
        try {

            emit(Event(Resource.loading(null)))
            val allList = apiHelper.getAllNews()
            Log.i("allList", "getPlants: $allList")

            allList.forEach { itemOfModel ->
                when {
                    itemOfModel.wide != null -> {
                        appDataBase.latestNewsDao()
                            .insertWideItem(itemOfModel.wide.mapToWideEntity())
                    }
                    itemOfModel.video != null -> {
                        appDataBase.latestNewsDao()
                            .insertVideoItem(itemOfModel.video.mapToVideoEntity())
                    }
                    itemOfModel.simple != null -> {
                        appDataBase.latestNewsDao()
                            .insertSimpleItem(itemOfModel.simple.mapToSimpleEntity())

                    }
                }
            }
            appDataBase.latestNewsDao()
                .insertLatestNewsItem(allList.map { it.mapToLatestNewEntity() } as MutableList)
            val data = appDataBase.latestNewsDao().getAll()

            emit(Event(responseHandler.handleSuccess(data)))

        } catch (e: Exception) {
            Log.i("Exception", "getAllFromApiAndObserve : ")
            emit(Event(responseHandler.handleException<List<RelationMain>>(e)))
        }

    }

}