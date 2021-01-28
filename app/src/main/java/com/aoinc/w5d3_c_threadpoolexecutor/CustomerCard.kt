package com.aoinc.w5d3_c_threadpoolexecutor

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView

class CustomerCard(
    context: Context, val attributeSet: AttributeSet
): CardView(context, attributeSet) {

    private lateinit var customerNameTextView : TextView
    private lateinit var serviceProgress : ProgressBar
    val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    init {
        layoutInflater.inflate(R.layout.customer_card_layout, this, true)
        customerNameTextView = rootView.findViewById(R.id.name)
        serviceProgress = rootView.findViewById(R.id.progress)

        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomerCard)
        val customerName = typedArray.getString(R.styleable.CustomerCard_CustomerName) ?: ""
        val progress = typedArray.getInt(R.styleable.CustomerCard_ServiceProgress, 0)

        customerNameTextView.text = customerName
        serviceProgress.progress = progress
    }

    fun setCustomerName(name: String) {
        customerNameTextView.text = name
//        invalidate()
//        requestLayout()
    }

    fun setProgress(progress: Int) {
        serviceProgress.progress = progress
//        invalidate()
//        requestLayout()
    }
}