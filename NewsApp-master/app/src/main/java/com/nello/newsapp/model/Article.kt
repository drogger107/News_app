package com.xuandq.newsapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import com.xuandq.newsapp.networking.RetrofitClient
import kotlinx.android.parcel.Parcelize


data class Article(
    val source: Source = Source("default","default"),
    val author : String = "anonymous",
    val title : String = "title",
    val description : String = "no description",
    val url : String = "default",
    val urlToImage : String = "default",
    val publishedAt : String = "default",
    val content : String = "default"
)  {

}