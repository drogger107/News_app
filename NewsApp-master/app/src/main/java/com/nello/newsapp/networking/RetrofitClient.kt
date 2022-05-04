package com.xuandq.newsapp.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class  RetrofitClient{
    private object Holder {
        val instance = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    companion object{
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "c75f419d95754540aa20a54e8c677fa8"
        @JvmStatic
        fun getInstance(): Retrofit {
            return Holder.instance
        }
    }
}