package com.aoinc.w5d3_c_threadpoolexecutor

import android.util.Log

class RestaurantCustomer(
    val customerName: String,
    private val timeToSpend: Int,
    private val listener: CustomerServiceListener
): Runnable {

    private val pricePerMinute = 5
    var serviceProgress: Double = 0.0

    interface CustomerServiceListener {
        fun UpdateServiceProgress(customer: RestaurantCustomer, progress: Double)
    }

    override fun run() {
        var totalCost = 0

        for (i in 1..timeToSpend ){
            Thread.sleep(1000)
            totalCost += pricePerMinute
            serviceProgress = (i/ timeToSpend.toDouble()) * 100
            Log.d("TAG_X", "$customerName is being served. Progress: ${serviceProgress.toInt()}%")
            listener.UpdateServiceProgress(this, serviceProgress)
        }
        Log.d("TAG_X", "$customerName is done. Bill is ${totalCost.convertToDollars()}")
    }

    // extension function! -> can be added onto existing objects
    // this extension function can be added onto an Int. :)
    private fun Int.convertToDollars(): String {
        return "$$this.00"
    }


}