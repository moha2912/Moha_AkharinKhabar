package com.example.akharinkhabar.data.local

import androidx.room.migration.Migration
/**
 * Created by moha on 2022-05-21.
 */
//data base migration
object DatabaseMigrations {
    const val DB_VERSION = 1

    val MIGRATIONS: Array<Migration>
        get() = arrayOf<Migration>(
        )
}