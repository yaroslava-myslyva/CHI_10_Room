package com.example.chi_10_room.db

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.chi_10_room.db.dao.BookStoreDao
import com.example.chi_10_room.db.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Database(
    version = 2,
    entities = [
        BookEntity::class,
        OrderEntity::class,
        CustomerEntity::class,
        OrderBookEntity::class
    ]
)
@TypeConverters(DateTypeConverter::class)
abstract class MyDataBase : RoomDatabase() {

    abstract val bookStoreDao: BookStoreDao


    private class DataBaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            instance?.let { instance ->
                scope.launch {
                    populateDatabase(instance.bookStoreDao)
                    db.execSQL("CREATE TRIGGER my_trigger AFTER INSERT ON customerEntity " +
                            "FOR EACH ROW BEGIN UPDATE customerEntity set birthday = 0; END;")
                }
            }
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
        }

        suspend fun populateDatabase(dao: BookStoreDao) {

            dao.addBooksList(
                listOf(
                    BookEntity(title = "Bible", author = "Good"),
                    BookEntity(title = "Hiba revut' voly...", author = "Panas Myrnyj"),
                    BookEntity(title = "Kobzar", author = "Taras Shevchenko")
                )
            )
            dao.addCustomersList(
                listOf(
                    CustomerEntity(name = "Nadija", birthday = Date(1994, 3, 5))
                )
            )

            dao.addOrderBookList(
                listOf(
                    OrderBookEntity(1, 1),
                    OrderBookEntity(1, 2),
                    OrderBookEntity(2, 2),
                    OrderBookEntity(2, 3)
                )
            )
            dao.addOrdersList(
                listOf(
                    OrderEntity(1, 1),
                    OrderEntity(2, 1)
                )
            )

        }

    }

    companion object {
        private const val DB_NAME = "myDataBase"
        private val Migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE CustomerEntity ADD COLUMN birthday LONG")
            }
        }

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
                    .addCallback(DataBaseCallback(scope))
                    .addMigrations(Migration_1_2)
                    .fallbackToDestructiveMigration()
                    .build()
                instance = newDb
                newDb
            }
        }
    }

}