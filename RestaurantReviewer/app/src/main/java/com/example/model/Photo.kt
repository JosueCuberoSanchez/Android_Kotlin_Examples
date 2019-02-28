package com.example.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Photo(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "name") val name: String
)