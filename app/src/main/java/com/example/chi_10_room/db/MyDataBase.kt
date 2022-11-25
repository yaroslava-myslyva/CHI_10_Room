package com.example.chi_10_room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chi_10_room.db.dao.CatalogDao
import com.example.chi_10_room.db.model.BookEntity
import com.example.chi_10_room.db.model.CustomerEntity
import com.example.chi_10_room.db.model.OrderEntity

@Database(
    entities = [
        BookEntity::class,
        OrderEntity::class,
        CustomerEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MyDataBase : RoomDatabase() {

    abstract val catalogDao :CatalogDao


}