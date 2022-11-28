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
     * Method for adding all books in the list
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBooksList(list: List<BookEntity>)

    /**
     * Method for adding all orders in the list
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrdersList(list: List<OrderEntity>)

    /**
     * Method for adding all customers in the list
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCustomersList(list: List<CustomerEntity>)

    /**
     * Method for adding connection between the order and the book in the list
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrderBookList(list: List<OrderBookEntity>)

    /**
     * Method for getting all customers
     */
    @Query("SELECT * FROM customerEntity")
    fun fetchCustomers(): LiveData<List<CustomerEntity>>

    /**
     * Method for joining customers with their books
     */
    @Query(
        "SELECT customerEntity.name AS name, b.title AS title, b.author AS author  " +
                "FROM bookEntity b, customerEntity " +
                "INNER JOIN orderBookEntity ob ON b.bookId = ob.bookId " +
                "INNER JOIN orderEntity o ON o.orderId = ob.orderId " +
                "INNER JOIN customerEntity c ON o.customerId = c.customerId"
    )
    fun joinCustomersAndBooks(): LiveData<List<CustomersAndBooks>>
}