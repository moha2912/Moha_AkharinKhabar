package com.example.akharinkhabar.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.akharinkhabar.data.model.LatestNew
import com.example.akharinkhabar.data.model.Simple

@Entity(tableName = "simple_item")
data class SimpleItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    var serverId: Long,
    var title: String,
    var desc: String,
    var publishDateFa: String,
    var categoryName: String,
    var likeCount: Long,
    var commentCount: Long,
    var viewCount: Long,
    var thumb: String,
)