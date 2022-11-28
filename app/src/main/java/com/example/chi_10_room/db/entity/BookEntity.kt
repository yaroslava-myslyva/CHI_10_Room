package com.example.chi_10_room.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookEntity")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val bookId: Int? = null,
    val title: String,
    val author: String
)