package com.example.demolinkchecker.di

import android.content.Context
import androidx.room.Room
import com.example.demolinkchecker.data.local.LinkDao
import com.example.demolinkchecker.data.local.LinkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LinkDatabase {
        return Room.databaseBuilder(
            context,
            LinkDatabase::class.java,
            "link_database"
        ).build()
    }

    @Provides
    fun provideWebsiteDao(database: LinkDatabase): LinkDao {
        return database.linkDAO()
    }
}
