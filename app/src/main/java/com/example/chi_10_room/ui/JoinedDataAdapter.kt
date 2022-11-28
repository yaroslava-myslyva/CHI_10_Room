package com.example.chi_10_room.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chi_10_room.CustomersAndBooks
import com.example.chi_10_room.databinding.CustomersBooksItemBinding

class JoinedDataAdapter : RecyclerView.Adapter<JoinedDataAdapter.JoinedDataHolder>() {
    private lateinit var customersBooks: List<CustomersAndBooks>

    fun updateList(list: List<CustomersAndBooks>) {
        customersBooks = list
        notifyDataSetChanged()
    }

    fun updateItem(position: Int){
        notifyItemChanged(position)
    }

    fun setupJoinedDataList(list :List<CustomersAndBooks>){
        this.customersBooks = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JoinedDataHolder {
        val binding = CustomersBooksItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return JoinedDataHolder(binding)
    }

    override fun onBindViewHolder(holder: JoinedDataHolder, position: Int) {
        val cb = customersBooks[position]
        holder.bind(cb)
    }

    override fun getItemCount(): Int {
        return customersBooks.size
    }

    inner class JoinedDataHolder(private val binding: CustomersBooksItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(customersBooks: CustomersAndBooks) {
            // binding.cbItemId.text = customersBooks.customerId.toString()
            binding.cbItemName.text = customersBooks.name
            binding.cbItemTitle.text = customersBooks.title
            binding.cbItemAuthor.text = customersBooks.author
        }
    }
}