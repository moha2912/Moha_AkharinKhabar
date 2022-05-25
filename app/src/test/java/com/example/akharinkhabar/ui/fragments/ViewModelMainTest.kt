package com.example.akharinkhabar.ui.fragments

import com.example.akharinkhabar.repository.FakeLatestNewsRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ViewModelMainTest {
    private lateinit var viewModelMain: ViewModelMain


    @Before
    fun setup(){
        viewModelMain = ViewModelMain(FakeLatestNewsRepository())
    }


}