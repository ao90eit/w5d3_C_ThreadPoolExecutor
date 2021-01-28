package com.aoinc.w5d3_c_threadpoolexecutor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(
    private var customerList: MutableList<RestaurantCustomer>
) : RecyclerView.Adapter<RVAdapter.CustomerServiceViewHolder>() {

    fun updateList(newList: MutableList<RestaurantCustomer>) {
        customerList = newList
        notifyDataSetChanged()
    }

    fun updateItem(customer: RestaurantCustomer, position: Int){
        customerList[position] = customer
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerServiceViewHolder =
        CustomerServiceViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false))

    override fun onBindViewHolder(holder: CustomerServiceViewHolder, position: Int) {
        val customer = customerList[position]

        holder.apply {
            name.text = customer.customerName
            progress.progress = customer.serviceProgress.toInt()
        }
    }

    override fun getItemCount(): Int =
        customerList.size

    inner class CustomerServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val progress: ProgressBar = itemView.findViewById(R.id.progress)
    }
}