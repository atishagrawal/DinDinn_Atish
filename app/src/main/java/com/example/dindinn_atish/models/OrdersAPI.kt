package com.example.dindinn_atish.models

import com.google.gson.annotations.SerializedName

data class OrdersAPI(
    @SerializedName("data")
    val orderData: List<Data>,
    val status: Status
)