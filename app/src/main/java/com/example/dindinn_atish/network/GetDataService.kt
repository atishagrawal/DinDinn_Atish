package com.example.dindinn_atish.network

import com.example.dindinn_atish.models.IngredientsAPI
import com.example.dindinn_atish.models.OrdersAPI
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Atish Agrawal on 15-05-2021.
 * Email: dir.atishagrawal@gmail.com
 * Phone: +91-9716987018
 */
interface GetDataService {

    @GET("610fc538a719b38c8e1e014fd87df267/raw/2ed9591349229cad27d867cef987276bc3cb50da/ordersapi")
    suspend fun getOrders(): OrdersAPI

    @GET("3c446c95b7d8517809dc9c02a566586e/raw/2ecd5f5f96a72542ccbdce5314379c17f778cb84/ingredients")
    suspend fun getAllIngredients(): IngredientsAPI

}