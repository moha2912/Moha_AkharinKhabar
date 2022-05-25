package com.example.akharinkhabar.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.akharinkhabar.data.model.LatestNew
import com.example.akharinkhabar.data.model.Simple

@Entity(tableName = "video_item")
data class VideoItem(
    @PrimaryKey
    var serverId: Long,
    var title: String,
    var thumb: String,
)