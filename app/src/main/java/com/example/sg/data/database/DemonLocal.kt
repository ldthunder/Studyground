package com.example.sg.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "demon")
data class DemonLocal(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(defaultValue = "")
    val thumbnail: String,
    @ColumnInfo(defaultValue = "")
    val url: String
    )