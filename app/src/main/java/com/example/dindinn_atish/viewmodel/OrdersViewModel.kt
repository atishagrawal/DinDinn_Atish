package com.example.dindinn_atish.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dindinn_atish.models.OrdersAPI
import com.example.dindinn_atish.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Created by Atish Agrawal on 26-06-2021.
 * Email: dir.atishagrawal@gmail.com
 * Phone: +91-9716987018
 */
class OrdersViewModel : ViewModel() {

    val TAG = OrdersViewModel::class.java.simpleName


    val orders = MutableLiveData<OrdersAPI>()

    fun getOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            orders.postValue(RetrofitClient().getInstance()!!.getApi().getOrders())
        }
    }
}