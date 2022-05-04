package com.xuandq.newsapp.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.xuandq.newsapp.R
import com.xuandq.newsapp.activities.DetailNewsActivity
import com.xuandq.newsapp.activities.MainActivity
import com.xuandq.newsapp.adapter.NewsAdapter
import com.xuandq.newsapp.model.Article
import com.xuandq.newsapp.model.ResponseData
import com.xuandq.newsapp.networking.NewsApi
import com.xuandq.newsapp.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class NewsListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var listNews = ArrayList<Article>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh = view.findViewById(R.id.newsRefresh)
        recyclerView = view.findViewById(R.id.newsRecyclerView)
        newsAdapter = NewsAdapter(requireContext(), listNews)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        loadData()
        swipeRefresh.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            loadData()
        })


    }

    private fun loadData() {
        swipeRefresh.isRefreshing = true
        val retrofit = RetrofitClient.getInstance()
        val api = retrofit.create(NewsApi::class.java)
        val call =
            api.getNews("us", RetrofitClient.API_KEY).enqueue(object : Callback<ResponseData> {
                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>
                ) {
                    if (!response.isSuccessful) {
                        Log.d("AAA", "onResponse: " + response.message())
                        hideRefresh()
                        return
                    }
                    for (news in response.body()?.articles!!) {
                        Log.d("AAA", "onResponse: " + news)
                    }
                    listNews.clear()
                    listNews.addAll(response.body()?.articles as ArrayList<Article>)
                    newsAdapter = NewsAdapter(context, listNews)
                    recyclerView.adapter = newsAdapter
                    hideRefresh()
                    newsAdapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
                        override fun onClick(news: Article) {
                            Log.d("AAA", "onClick: " + news)
                            val intent = Intent(requireActivity(), DetailNewsActivity::class.java)
                            if (news.author == null) intent.putExtra("author", "")
                            else intent.putExtra("author", news.author)
                            if (news.title == null) intent.putExtra("title", "")
                            else intent.putExtra("title", news.title)
                            if (news.url == null) intent.putExtra("url", "")
                            else intent.putExtra("url", news.url)
                            if (news.publishedAt == null) intent.putExtra("date", "")
                            else intent.putExtra("date", news.publishedAt)

                            startActivity(intent)

                        }

                    })
                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    hideRefresh()
                    Log.d("AAA", "onFailure: " + t.message)
                }
            })
    }

    private fun hideRefresh() {
        val hander: Handler = Handler()
        hander.postDelayed(Runnable {
            swipeRefresh.isRefreshing = false
        }, 200)
    }

}