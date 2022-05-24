package com.example.akharinkhabar.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.akharinkhabar.data.model.LatestNew
import com.example.akharinkhabar.data.model.Simple

@Entity(tableName = "latestNews_item")
data class LatestNewsItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var simpleId: Long,
    var wideId: Long,
    var videoId: Long
)