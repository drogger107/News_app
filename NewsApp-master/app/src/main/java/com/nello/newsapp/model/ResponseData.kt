package com.xuandq.newsapp.model

data class ResponseData(
    val status : String = "default",
    val totalResults : Int = 0,
    val articles : List<Article> = ArrayList<Article>()
) {
}

