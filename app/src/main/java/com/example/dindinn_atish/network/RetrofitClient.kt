package com.example.dindinn_atish.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Atish Agrawal on 15-05-2021.
 * Email: dir.atishagrawal@gmail.com
 * Phone: +91-9716987018
 */
class RetrofitClient {


    @Synchronized
    fun getInstance(): RetrofitClient? {
        if (mInstance == null) {
            mInstance = RetrofitClient()
        }
        return mInstance
    }

    private fun RetrofitClient(): RetrofitClient {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient().newBuilder().addInterceptor(logging).build()

        retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
        return this
    }

    fun getApi(): GetDataService {
        return retrofit!!.create(GetDataService::class.java)
    }


    companion object {
        var mInstance: RetrofitClient? = null
        var retrofit: Retrofit? = null

        val BASE_URL = "https://gist.githubusercontent.com/atishagrawal/"

    }

}