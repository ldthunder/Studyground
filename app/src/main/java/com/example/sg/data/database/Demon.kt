package com.example.sg.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Demon(
    @PrimaryKey val id: Int,
    val name: String,
    val power: Int
    )