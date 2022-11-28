package com.example.chi_10_room.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.chi_10_room.CustomersAndBooks
import com.example.chi_10_room.app.MyApplication
import com.example.chi_10_room.db.entity.BookEntity
import com.example.chi_10_room.db.entity.CustomerEntity
import com.example.chi_10_room.db.entity.OrderBookEntity
import com.example.chi_10_room.db.entity.OrderEntity
import kotlinx.coroutines.launch

class MainViewModel(private val app: MyApplication) : ViewModel() {
    val db = app.dataBase.bookStoreDao

    fun addBooksList(list: List<BookEntity>) {
        app.applicationScope.launch {
            db.addBooksList(list)
        }
    }

    fun addOrdersList(list: List<OrderEntity>) {
        app.applicationScope.launch {
            db.addOrdersList(list)
        }
    }

    fun addOrderBookList(list: List<OrderBookEntity>) {
        app.applicationScope.launch {
            db.addOrderBookList(list)
        }
    }

    fun addCustomersList(list: List<CustomerEntity>) {
        app.applicationScope.launch {
            db.addCustomersList(list)
        }
    }

    fun fetchCustomersLiveData() = db.fetchCustomers()

    fun joinCustomersAndBooks() = db.joinCustomersAndBooks()

    fun deleteAll(){
        app.applicationScope.launch {
            db.deleteAll()
        }
    }

    fun createTrigger(){
        app.applicationScope.launch {
            db.createTrigger(SimpleSQLiteQuery("ALTER SEQUENCE bookEntity RESTART 1;"))
        }
    }
}