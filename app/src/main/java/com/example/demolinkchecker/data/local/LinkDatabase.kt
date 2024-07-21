package com.example.demolinkchecker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LinkModel::class], version = 1)
abstract class LinkDatabase : RoomDatabase() {
    abstract fun linkDAO(): LinkDao

    companion object {
        @Volatile private var INSTANCE: LinkDatabase? = null

        fun getDatabase(context: Context): LinkDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LinkDatabase::class.java,
                    "link_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}