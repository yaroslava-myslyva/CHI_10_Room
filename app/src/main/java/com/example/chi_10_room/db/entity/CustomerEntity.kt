package com.example.chi_10_room.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customerEntity")
data class CustomerEntity(
    @PrimaryKey val customerId: Int,
    val name: String,
    val age :Int = 0
)