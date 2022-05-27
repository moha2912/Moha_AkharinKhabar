package com.example.akharinkhabar.ui.fragments

import androidx.lifecycle.*
import com.example.akharinkhabar.data.model.db.LatestNewsItem
import com.example.akharinkhabar.data.model.db.RelationMain
import com.example.akharinkhabar.data.repository.InRepositoryMain
import com.example.akharinkhabar.data.repository.RepositoryMain
import com.example.akharinkhabar.other.Event
import com.example.akharinkhabar.other.Resource
import com.example.akharinkhabar.other.ResponseHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Moha on 2022-05-21.
 */

@HiltViewModel
class ViewModelMain @Inject constructor(
    private val mainRepository: RepositoryMain,
) : ViewModel() {

    val list = mainRepository.getValueFromDbLiveData()


    var data: Flow<Event<Resource<Boolean>>>? = null

    fun test() {
        viewModelScope.launch {
            data = mainRepository.getAllFromApiAndObserve()
        }
    }

//    fun getValueFromDbLiveData() {
//        viewModelScope.launch {
//            val response = mainRepository.getValueFromDbLiveData()
//
//            _values.value = Event(responseHandler.handleSuccess(response))
//        }
//
//    }


}