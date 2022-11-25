package com.example.chi_10_room.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookEntity")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    val author: String
)