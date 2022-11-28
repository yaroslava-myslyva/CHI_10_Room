package com.example.chi_10_room.db.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.chi_10_room.CustomersAndBooks
import com.example.chi_10_room.db.entity.BookEntity
import com.example.chi_10_room.db.entity.CustomerEntity
import com.example.chi_10_room.db.entity.OrderBookEntity
import com.example.chi_10_room.db.entity.OrderEntity

@Dao
interface BookStoreDao {

    /**
     *
     */

    @RawQuery()
    suspend fun createTrigger(query: SupportSQLiteQuery) = 1

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBooksList(list: List<BookEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrdersList(list: List<OrderEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCustomersList(list: List<CustomerEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrderBookList(list: List<OrderBookEntity>)

    @Query("SELECT * FROM customerEntity")
    fun fetchCustomers(): LiveData<List<CustomerEntity>>

    @Query(
        "SELECT customerEntity.name AS name, b.title AS title, b.author AS author  " +
                "FROM bookEntity b, customerEntity " +
                "INNER JOIN orderBookEntity ob ON b.bookId = ob.bookId " +
                "INNER JOIN orderEntity o ON o.orderId = ob.orderId " +
                "INNER JOIN customerEntity c ON o.customerId = c.customerId"
    )
    fun joinCustomersAndBooks() :LiveData<List<CustomersAndBooks>>

    @Query("DELETE FROM bookEntity")
    suspend fun deleteAllBooks()

    @Query("DELETE FROM orderEntity")
    suspend fun deleteAllOrders()

    @Query("DELETE FROM orderBookEntity")
    suspend fun deleteAllOrderBook()

    @Query("DELETE FROM customerEntity")
    suspend fun deleteAllCustomers()

    @Transaction
    suspend fun deleteAll() {
        deleteAllOrderBook()
        deleteAllOrders()
        deleteAllCustomers()
        deleteAllBooks()
    }


}