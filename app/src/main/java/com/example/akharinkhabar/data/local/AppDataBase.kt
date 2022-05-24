package com.example.akharinkhabar.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.akharinkhabar.data.*
import com.example.akharinkhabar.data.model.db.LatestNewsItem
import com.example.akharinkhabar.data.model.db.SimpleItem
import com.example.akharinkhabar.data.model.db.VideoItem
import com.example.akharinkhabar.data.model.db.WideItem

/**
 * Created by moha on 2022-05-21.
 */

//Create database
@Database(
    entities = [LatestNewsItem::class, WideItem::class, VideoItem::class, SimpleItem::class],


    version = DatabaseMigrations.DB_VERSION,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    //region Dao
    abstract fun latestNewsDao(): LatestNewsDao
    //endregion

    companion object {
        const val DB_NAME = "latestNews_db"

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    DB_NAME
                ).addMigrations(*DatabaseMigrations.MIGRATIONS).build()

                INSTANCE = instance
                return instance
            }
        }
    }

}