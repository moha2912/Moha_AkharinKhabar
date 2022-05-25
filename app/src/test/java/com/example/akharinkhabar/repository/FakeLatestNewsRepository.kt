package com.example.akharinkhabar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.akharinkhabar.data.model.ErrorModel
import com.example.akharinkhabar.data.model.LatestNew
import com.example.akharinkhabar.data.model.Simple
import com.example.akharinkhabar.data.model.Video
import com.example.akharinkhabar.data.model.db.LatestNewsItem
import com.example.akharinkhabar.data.model.db.RelationMain
import com.example.akharinkhabar.data.repository.InRepositoryMain
import com.example.akharinkhabar.data.repository.RepositoryMain
import com.example.akharinkhabar.other.Event
import com.example.akharinkhabar.other.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Moha on 2022-05-25.
 */

class FakeLatestNewsRepository : InRepositoryMain {

    private val latestItems = mutableListOf<LatestNew>()
//    private val lists = mutableListOf<List<RelationMain>>()

    private val _lists = MutableLiveData<List<RelationMain>>()
    val lists: LiveData<List<RelationMain>> = _lists

    private val observerLatestItems = MutableLiveData<List<LatestNew>>(latestItems)

    private var shouldReturnNetworkError = false


    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getAllFromApiAndObserve(): Flow<Event<Resource<Boolean>>> = flow {
        if (shouldReturnNetworkError) {
            emit(Event(Resource.error(ErrorModel(), null)))
        } else {
            emit(Event(Resource.success(true)))
        }
    }
//
//    fun getAllFromApiAndObserve(): Resource<LatestNew> {
//        return if (shouldReturnNetworkError) {
//            Resource.error(ErrorModel("Error"), null)
//        } else {
//            Resource.success(LatestNew(simple = Simple(), wide = Video(), video = Video()))
//        }
//    }

    override fun getValueFromDbLiveData(): LiveData<List<RelationMain>> {
        return lists

    }

}