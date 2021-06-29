package com.example.dindinn_atish.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dindinn_atish.models.IngredientsAPI
import com.example.dindinn_atish.models.OrdersAPI
import com.example.dindinn_atish.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Created by Atish Agrawal on 26-06-2021.
 * Email: dir.atishagrawal@gmail.com
 * Phone: +91-9716987018
 */
class IngredientsViewModel : ViewModel() {

    val TAG = IngredientsViewModel::class.java.simpleName


    val ingredients = MutableLiveData<IngredientsAPI>()

    fun getAllIngredients() {
        viewModelScope.launch(Dispatchers.IO) {
            ingredients.postValue(RetrofitClient().getInstance()!!.getApi().getAllIngredients())
        }
    }
}