package com.example.chi_10_room.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.chi_10_room.db.dao.BookStoreDao
import com.example.chi_10_room.db.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(version = 1,
    entities = [
        BookEntity::class,
        OrderEntity::class,
        CustomerEntity::class,
        OrderBookEntity::class
    ]

)
abstract class MyDataBase : RoomDatabase() {

    abstract val bookStoreDao: BookStoreDao

//    private class DataBaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            instance?.let { instance ->
//                scope.launch {
//                    populateDatabase(instance.bookStoreDao)
//                }
//            }
//        }
//
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//        }
//
//        suspend fun populateDatabase(dao: BookStoreDao) {
//
//        }
//
//    }

    companion object {
        private const val DB_NAME = "myDataBase"

        @Volatile
        private var instance: MyDataBase? = null

        fun getDataBase(
            context: Context,
            scope: CoroutineScope
        ): MyDataBase {
            return instance ?: synchronized(this) {

                val newDb = Room.databaseBuilder(
                    context.applicationContext,
                    MyDataBase::class.java,
                    DB_NAME
                )
                   // .addCallback(DataBaseCallback(scope))
                    //.fallbackToDestructiveMigration()
                    .build()
                instance = newDb
                newDb
            }
        }
    }

}