package com.example.chi_10_room.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = ["orderId", "bookId"],
    tableName = "orderBookEntity"
)
data class OrderBookEntity(
    val orderId: Int,
    val bookId: Int
)