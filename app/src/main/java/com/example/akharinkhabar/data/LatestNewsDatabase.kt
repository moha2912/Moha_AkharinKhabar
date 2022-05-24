package com.example.akharinkhabar.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.akharinkhabar.data.model.db.LatestNewsItem
import com.example.akharinkhabar.data.model.db.SimpleItem
import com.example.akharinkhabar.data.model.db.VideoItem
import com.example.akharinkhabar.data.model.db.WideItem

@Database(
    entities = [LatestNewsItem::class, WideItem::class, VideoItem::class, SimpleItem::class],
    version = 1
)
abstract class LatestNewsDatabase : RoomDatabase() {

    abstract fun mainDao(): LatestNewsDao
}