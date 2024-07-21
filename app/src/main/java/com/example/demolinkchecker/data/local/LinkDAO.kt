package com.example.demolinkchecker.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface LinkDao {
    @Query("SELECT * FROM links")
    fun getAllLink(): LiveData<List<LinkModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLink(link: LinkModel)

    @Update
    suspend fun updateLink(link: LinkModel)

    @Delete
    suspend fun deleteLink(link: LinkModel)


    @Query("UPDATE links SET status = :status WHERE id = :id")
    suspend fun updateStatus(id: Int, status: Boolean)
}
