package com.example.demolinkchecker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "links")
data class LinkModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val time: String,
    val status: Boolean,
    val isStarted : Boolean
)