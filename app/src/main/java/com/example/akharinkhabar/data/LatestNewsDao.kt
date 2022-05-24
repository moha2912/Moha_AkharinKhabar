package com.example.akharinkhabar.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.akharinkhabar.data.model.db.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LatestNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLatestNewsItem(latestNewsItem: LatestNewsItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLatestNewsItem(latestNewsItem: List<LatestNewsItem>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSimpleItem(simpleItem: SimpleItem)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWideItem(wideItem: WideItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideoItem(videoItem: VideoItem)

    @Delete
    suspend fun deleteLatestNewsItem(shoppingItem: LatestNewsItem)

    @Query("SELECT * FROM latestNews_item")
    fun observeAllLatestNewsItem(): LiveData<List<LatestNewsItem>>

    @Query("SELECT * FROM latestNews_item ORDER BY id")
    suspend fun getAll(): List<RelationMain>

    @Query("SELECT * FROM latestNews_item WHERE id = :growZoneNumber ORDER BY id")
    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int): Flow<List<RelationMain>>
}