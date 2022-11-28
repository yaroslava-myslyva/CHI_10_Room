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

    fun addCustomersList(list: List<CustomerEntity>) {
        app.applicationScope.launch {
            db.addCustomersList(list)
        }
    }

    fun fetchCustomersLiveData() = db.fetchCustomers()

    fun joinCustomersAndBooks() = db.joinCustomersAndBooks()

}