package com.aoinc.w5d3_c_threadpoolexecutor

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator


class MainActivity : AppCompatActivity(),
    Handler.Callback, RestaurantCustomer.CustomerServiceListener {

    private lateinit var handlerUtil: HandlerUtil
    private lateinit var handler: Handler

    private lateinit var customerList: RecyclerView
    private val rvAdapter = RVAdapter(mutableListOf())

    private val restaurantCustomer = mutableListOf<RestaurantCustomer>(
        RestaurantCustomer("Sherry", 30, this),
        RestaurantCustomer("Ralph", 10, this),
        RestaurantCustomer("Diep", 23, this),
        RestaurantCustomer("Waldo", 3, this),
        RestaurantCustomer("Dr. Bashir", 22, this),
        RestaurantCustomer("Neo", 14, this),
        RestaurantCustomer("Kyle", 19, this),
        RestaurantCustomer("Miranda", 28, this),
        RestaurantCustomer("Kerry", 7, this)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customerList = findViewById(R.id.recycler_view)
        (customerList.getItemAnimator() as SimpleItemAnimator).supportsChangeAnimations = false
        rvAdapter.updateList(restaurantCustomer)
        customerList.adapter = rvAdapter

        handler = Handler(Looper.getMainLooper(), this)
        handlerUtil = HandlerUtil(handler, restaurantCustomer)
        handlerUtil.serveExistingCustomers()

//        val customCard: CustomerCard = findViewById(R.id.test_customer_card)
//        customCard.setCustomerName("James")
//        customCard.setProgress(44)
    }

    override fun handleMessage(msg: Message): Boolean {
        Log.e("TAG_X", "in handle message")
        return true
    }

    override fun UpdateServiceProgress(customer: RestaurantCustomer, progress: Double) {
        runOnUiThread(){
            val index = restaurantCustomer.indexOf(customer)
            rvAdapter.updateItem(customer, index)
        }
    }
}