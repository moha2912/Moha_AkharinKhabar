package com.example.akharinkhabar.local.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.akharinkhabar.data.LatestNewsDao
import com.example.akharinkhabar.data.LatestNewsDatabase
import com.example.akharinkhabar.data.model.db.LatestNewsItem
import com.example.akharinkhabar.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Moha on 2022-05-25.
 */

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class LatestDaoTest {

    @get:Rule
    var insertTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: LatestNewsDatabase
    private lateinit var dao: LatestNewsDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LatestNewsDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.mainDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertTestITem() = runTest {
        val testItem = LatestNewsItem(id = 0, simpleId = 1L, wideId = 0L, videoId = 0L)
        dao.insertLatestNewsItem(testItem)

        val allItems = dao.observeAllShoppingItemsTest().getOrAwaitValue()

        assertThat(allItems).contains(testItem)

    }

    @Test
    fun deleteShoppingItem() = runTest {
        val shoppingItem = LatestNewsItem(id = 0, simpleId = 1L, wideId = 0L, videoId = 0L)
        dao.insertLatestNewsItem(shoppingItem)

        dao.deleteLatestNewsItem(shoppingItem)

        val shoppingItems = dao.observeAllShoppingItemsTest().getOrAwaitValue()

        assertThat(shoppingItems).doesNotContain(shoppingItem)
    }

}
