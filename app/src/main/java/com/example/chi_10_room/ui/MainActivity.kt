package com.example.chi_10_room.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chi_10_room.app.MyApplication
import com.example.chi_10_room.databinding.ActivityMainBinding
import com.example.chi_10_room.db.dao.BookStoreDao
import com.example.chi_10_room.db.entity.CustomerEntity
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var app: MyApplication
    private lateinit var db: BookStoreDao
    private var customerAdapter: CustomerAdapter? = null
    private var cbAdapter: JoinedDataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        app = application as MyApplication
        viewModel = MainViewModel(app)
        setButtonsOnClickListeners()
    }

    private fun setButtonsOnClickListeners() {
        binding.buttonSetupCustomers.setOnClickListener {
            setupCustomers()
        }
        binding.buttonJoin.setOnClickListener {
            setupJoinedRecyclerview()
        }

        binding.buttonAddNewCustomer.setOnClickListener {
            viewModel.addCustomersList(
                listOf(
                    CustomerEntity(name = "Sashko", birthday = Date(1985, 3, 6))
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

}