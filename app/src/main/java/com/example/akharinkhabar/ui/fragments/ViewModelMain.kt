package com.example.akharinkhabar.ui.fragments

import androidx.lifecycle.*
import com.example.akharinkhabar.data.model.db.LatestNewsItem
import com.example.akharinkhabar.data.model.db.RelationMain
import com.example.akharinkhabar.data.repository.RepositoryMain
import com.example.akharinkhabar.other.Event
import com.example.akharinkhabar.other.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Moha on 2022-05-21.
 */

@HiltViewModel
class ViewModelMain @Inject internal constructor(
    private val mainRepository: RepositoryMain,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _data = MutableSharedFlow<Event<Resource<List<RelationMain>>>>()
    var data: Flow<Event<Resource<List<RelationMain>>>>? = null

    fun test() {
        viewModelScope.launch {
            data = mainRepository.getAllFromApiAndObserve()
        }
    }

}