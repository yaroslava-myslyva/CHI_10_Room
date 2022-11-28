package com.example.chi_10_room.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chi_10_room.databinding.CustomerItemBinding
import com.example.chi_10_room.db.entity.CustomerEntity
import java.text.SimpleDateFormat
import java.util.*

class CustomerAdapter : RecyclerView.Adapter<CustomerAdapter.CustomerHolder>() {
    private lateinit var customers: List<CustomerEntity>

    fun setupCustomersList(list: List<CustomerEntity>) {
        this.customers = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerHolder {
        val binding = CustomerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CustomerHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerHolder, position: Int) {
        val client = customers[position]
        holder.bind(client)
    }

    override fun getItemCount(): Int {
        return customers.size
    }

    inner class CustomerHolder(private val binding: CustomerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(customer: CustomerEntity) {
            binding.clientItemId.text = customer.customerId.toString()
            binding.clientItemName.text = customer.name
            val sdf = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
            binding.clientItemBirthday.text = sdf.format(customer.birthday)
        }
    }
}