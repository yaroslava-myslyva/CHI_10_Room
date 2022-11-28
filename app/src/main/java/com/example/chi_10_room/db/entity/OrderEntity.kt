package com.example.chi_10_room.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "orderEntity",
    foreignKeys = [ForeignKey(
        entity = CustomerEntity::class,
        parentColumns = arrayOf("customerId"),
        childColumns = arrayOf("customerId")
    )]
)
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val orderId: Int? = null,
    val customerId: Int
)