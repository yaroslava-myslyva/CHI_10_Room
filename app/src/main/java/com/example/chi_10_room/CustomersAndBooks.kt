package com.example.chi_10_room

import androidx.room.Embedded
import androidx.room.Relation
import com.example.chi_10_room.db.entity.BookEntity
import com.example.chi_10_room.db.entity.CustomerEntity
import com.example.chi_10_room.db.entity.OrderEntity
import java.nio.file.attribute.UserPrincipal

data class CustomersAndBooks(
    val name :String,
    val title :String,
    val author :String
)
