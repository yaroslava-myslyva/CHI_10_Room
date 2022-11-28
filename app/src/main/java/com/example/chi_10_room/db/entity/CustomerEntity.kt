package com.example.chi_10_room.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.chi_10_room.db.DateTypeConverter
import java.util.*

@Entity(tableName = "customerEntity")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true) val customerId: Int? = null,
    val name: String,
    val birthday: Date
)