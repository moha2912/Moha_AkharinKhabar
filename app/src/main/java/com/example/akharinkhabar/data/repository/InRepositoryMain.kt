package com.example.akharinkhabar.data.repository

import androidx.lifecycle.LiveData
import com.example.akharinkhabar.data.model.db.RelationMain
import com.example.akharinkhabar.other.Event
import com.example.akharinkhabar.other.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Moha on 2022-05-10.
 */

interface InRepositoryMain {

    suspend fun getAllFromApiAndObserve() : Flow<Event<Resource<Boolean>>>

     fun getValueFromDbLiveData() : LiveData<List<RelationMain>>

}