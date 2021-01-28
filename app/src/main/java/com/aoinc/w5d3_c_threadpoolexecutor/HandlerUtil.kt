package com.aoinc.w5d3_c_threadpoolexecutor

import android.os.Handler
import java.util.concurrent.*

// Handler -> passes messages into the thread's message queue
// (Looper -> loops around through the messages passed by the handler)
class HandlerUtil(
    private val handlerUtil: Handler,
    private val customerList: List<RestaurantCustomer>
) {

    private val CORE_POOL_SIZE = 3
    private val MAX_POOL_SIZE = 5
    private val KEEP_ALIVE_TIME = 10L

    private lateinit var restaurantManager: ThreadPoolExecutor

    private val customerQueue: BlockingQueue<Runnable> = LinkedBlockingQueue()

    fun serveExistingCustomers() {
        if (!this::restaurantManager.isInitialized) {
            restaurantManager = ThreadPoolExecutor(
                CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                TimeUnit.SECONDS, customerQueue
            )

            customerList.forEach {
                restaurantManager.execute(it)
            }
        }
    }
}