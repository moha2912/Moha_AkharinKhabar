package com.example.akharinkhabar.data.model

import com.example.akharinkhabar.data.model.db.LatestNewsItem
import com.example.akharinkhabar.data.model.db.SimpleItem
import com.example.akharinkhabar.data.model.db.VideoItem
import com.example.akharinkhabar.data.model.db.WideItem

data class LatestNew(
    val simple: Simple? = null,
    val wide: Video? = null,
    val video: Video? = null
) {

    fun mapToLatestNewEntity() = LatestNewsItem(
        id = when {
            wide != null ->
                wide.id
            video != null ->
                video.id
            simple != null ->
                simple.id
            else -> 0
        },
        simpleId = simple?.id ?: 0,
        wideId = wide?.id ?: 0,
        videoId = video?.id ?: 0,
    )
}

data class Simple(
    val id: Long,
    val title: String,
    val desc: String,
    val publishDateFa: String,
    val categoryName: String,
    val likeCount: Long,
    val commentCount: Long,
    val viewCount: Long,
    val thumb: String
) {
    fun mapToSimpleEntity() = SimpleItem(
        id = id,
        serverId = id,
        title = title,
        thumb = thumb,
        desc = desc,
        publishDateFa = publishDateFa,
        categoryName = categoryName,
        likeCount = likeCount,
        commentCount = commentCount,
        viewCount = viewCount
    )
}

data class Video(
    val id: Long,
    val title: String,
    val thumb: String,
    val categoryName: String? = null
) {
    fun mapToWideEntity() = WideItem(
        id = id,
        serverId = id,
        title = title,
        thumb = thumb,
        categoryName = categoryName ?: ""
    )

    fun mapToVideoEntity() = VideoItem(
        id = id,
        serverId = id,
        title = title,
        thumb = thumb
    )
}

