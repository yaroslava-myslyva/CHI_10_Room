package com.example.chi_10_room.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customerEntity")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val age :Int?
)