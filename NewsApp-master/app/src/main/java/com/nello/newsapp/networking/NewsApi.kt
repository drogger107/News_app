package com.xuandq.newsapp.networking

import com.xuandq.newsapp.model.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    fun getNews(@Query("country") country:String,@Query("apiKey") apiKey:String):Call<ResponseData>
    @GET("everything")
    fun getSearchNews(@Query("q") q:String,@Query("apiKey") apiKey: String) : Call<ResponseData>
}