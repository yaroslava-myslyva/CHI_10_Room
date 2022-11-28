package com.example.chi_10_room.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chi_10_room.app.MyApplication
import com.example.chi_10_room.databinding.ActivityMainBinding
import com.example.chi_10_room.db.dao.BookStoreDao
import com.example.chi_10_room.db.entity.BookEntity
import com.example.chi_10_room.db.entity.CustomerEntity
import com.example.chi_10_room.db.entity.OrderBookEntity
import com.example.chi_10_room.db.entity.OrderEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var app: MyApplication
    private lateinit var db: BookStoreDao
    private var customerAdapter: CustomerAdapter? = null
    private var cbAdapter: JoinedDataAdapter? = null

    // засунуть книги
    // покупателей с заказами с книгами
    // вытащить покупателя и его книги

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        app = application as MyApplication
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel = MainViewModel(app)

        setButtonsOnClickListeners()
    }

    private fun setButtonsOnClickListeners() {
        binding.buttonCreate.setOnClickListener {
            //viewModel.createTrigger()
            deleteAll()
            viewModel.addBooksList(
                listOf(
                    BookEntity(1, title = "Bible", author = "Good"),
                    BookEntity(2, title = "Hiba revut' voly...", author = "Panas Myrnyj"),
                    BookEntity(3, title = "Kobzar", author = "Taras Shevchenko")
                )
            )
            viewModel.addCustomersList(
                listOf(
                    CustomerEntity(customerId = 1, name = "Nadija")
                )
            )

            viewModel.addOrderBookList(
                listOf(
                    OrderBookEntity(1, 1),
                    OrderBookEntity(1, 2),
                    OrderBookEntity(2, 2),
                    OrderBookEntity(2, 3)
                )
            )
            viewModel.addOrdersList(
                listOf(
                    OrderEntity(1, 1),
                    OrderEntity(2, 1)
                )
            )
        }

        binding.buttonSetupCustomers.setOnClickListener {
            setupCustomers()
        }

        binding.buttonDeleteAll.setOnClickListener {
            deleteAll()
        }

        binding.buttonJoin.setOnClickListener {
            setupJoinedRecyclerview()
        }
//
//        binding.buttonMigrate.setOnClickListener {
//            version = 2
//            dbManager?.dataBaseHelper?.onUpgrade(dbManager?.dataBaseHelper?.writableDatabase, 1, 2)
//            dbManager = DBManager(this)
//            it.isClickable = false
//        }
//
//        binding.buttonAddTrigger.setOnClickListener {
//            dbManager?.createTrigger()
//        }
//
        binding.buttonAddNewCustomer.setOnClickListener {
            viewModel.addCustomersList(
                listOf(
                    CustomerEntity(customerId = 2, name = "Sashko")
                )
            )
        }
    }

    private fun setupCustomers() {
        viewModel.fetchCustomersLiveData().observe(this@MainActivity) { list ->
            customerAdapter = CustomerAdapter()

            customerAdapter?.setupCustomersList(
                list
            )
            binding.customersList.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.customersList.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager(this@MainActivity).orientation
                )
            )
            binding.customersList.adapter = customerAdapter
            binding.layoutCustomers.visibility = View.VISIBLE
        }
    }

    private fun setupJoinedRecyclerview() {
        viewModel.joinCustomersAndBooks().observe(this@MainActivity) { list ->
            cbAdapter = JoinedDataAdapter()

            cbAdapter?.setupJoinedDataList(list)
            binding.customersBooksList.layoutManager = LinearLayoutManager(this)
            binding.customersBooksList.addItemDecoration(
                DividerItemDecoration(
                    this,
                    LinearLayoutManager(this).orientation
                )
            )
            binding.customersBooksList.adapter = cbAdapter
            binding.layoutCustomersBooks.visibility = View.VISIBLE
        }
    }

    private fun deleteAll() {
        viewModel.deleteAll()

        binding.layoutCustomers.visibility = View.GONE
        customerAdapter?.setupCustomersList(emptyList())
        binding.customersList.adapter = customerAdapter

        binding.layoutCustomersBooks.visibility = View.GONE
        cbAdapter?.setupJoinedDataList(emptyList())
        binding.customersBooksList.adapter = cbAdapter
    }
}