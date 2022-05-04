package com.xuandq.newsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.xuandq.newsapp.R
import com.xuandq.newsapp.databinding.ActivityDetailNewsBinding
import com.xuandq.newsapp.model.Article

class DetailNewsActivity : AppCompatActivity() {
    private lateinit var webView : WebView
    private lateinit var binding: ActivityDetailNewsBinding
    private var data : Article?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_news)
        webView = findViewById(R.id.detail_webview)
        val toolbar : Toolbar = findViewById(R.id.detail_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        getData()
        binding.apply {
            binding.news = data
        }
        supportActionBar?.setTitle(data?.url)
        webView.loadUrl(data!!.url)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getData() {
        val title : String = intent.extras?.get("title") as String
        val author : String = intent.extras?.get("author") as String
        val date = intent.extras?.get("date") as String
        val url = intent.extras?.get("url") as String
        data = Article(title = title ,author = author,publishedAt = date,url = url)
    }
}