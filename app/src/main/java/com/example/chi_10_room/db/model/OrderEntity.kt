package com.example.chi_10_room.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orderEntity")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val customerId :Int
)