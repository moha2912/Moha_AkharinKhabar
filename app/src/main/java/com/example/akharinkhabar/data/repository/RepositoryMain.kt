package com.example.akharinkhabar.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
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

open class RepositoryMain @Inject constructor(
    private val appDataBase: AppDataBase,
    private val apiHelper: ApiHelper
) : InRepositoryMain{
    private val responseHandler = ResponseHandler()

  override suspend fun getAllFromApiAndObserve(): Flow<Event<Resource<Boolean>>> = flow {
        try {

            emit(Event(Resource.loading(null)))
            val allList = apiHelper.getAllNews()
            Log.i("allList", "getPlants: $allList")
            var localId = 0L
            allList.forEach { itemOfModel ->
                when {
                    itemOfModel.wide != null -> {
                        localId = appDataBase.latestNewsDao()
                            .insertWideItem(itemOfModel.wide.mapToWideEntity())
                    }
                    itemOfModel.video != null -> {
                        localId = appDataBase.latestNewsDao()
                            .insertVideoItem(itemOfModel.video.mapToVideoEntity())
                    }
                    itemOfModel.simple != null -> {
                        localId = appDataBase.latestNewsDao()
                            .insertSimpleItem(itemOfModel.simple.mapToSimpleEntity())

                    }
                }

                appDataBase.latestNewsDao()
                    .insertLatestNewsItem(itemOfModel.mapToLatestNewEntity(localId))


            }

            emit(Event(responseHandler.handleSuccess(true)))

        } catch (e: Exception) {
            Log.i("Exception", "getAllFromApiAndObserve : $e")
            emit(Event(responseHandler.handleException<Boolean>(e)))
        }

    }

    override fun getValueFromDbLiveData(): LiveData<List<RelationMain>> {
        return appDataBase.latestNewsDao().observeAllLatestNewsItem()
    }

}