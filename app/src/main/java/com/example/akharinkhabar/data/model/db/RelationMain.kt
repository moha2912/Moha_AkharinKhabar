package com.example.akharinkhabar.data.model.db

import androidx.room.Embedded
import androidx.room.Relation
import com.example.akharinkhabar.data.model.LatestNew

/**
 * Created by Moha on 2022-05-22.
 */

data class RelationMain(
    @Embedded var latestNewsItem: LatestNewsItem,
    @Relation(
        entity = VideoItem::class,
        parentColumn = "videoId",
        entityColumn = "serverId",
    )
    var video: VideoItem?,
    @Relation(
        entity = WideItem::class,
        parentColumn = "wideId",
        entityColumn = "serverId",
    )
    val wide: WideItem?,
    @Relation(
        entity = SimpleItem::class,
        parentColumn = "simpleId",
        entityColumn = "serverId",
    )
    val simple: SimpleItem?

)