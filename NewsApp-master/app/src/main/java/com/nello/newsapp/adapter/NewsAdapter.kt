package com.xuandq.newsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.xuandq.newsapp.databinding.ItemNewsBinding
import com.xuandq.newsapp.databinding.ItemNewsWithoutImgBinding
import com.xuandq.newsapp.model.Article

class NewsAdapter(val context: Context?, val listNews: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.BaseViewHoler>() {
    private val IMAGE = 0
    private val NO_IMAGE = 1
    private var listener : OnItemClickListener? = null

    abstract class BaseViewHoler(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(news: Article)
    }

    class NewsViewHolder(val binding: ItemNewsBinding) : BaseViewHoler(binding) {
        override fun bind(news: Article) {
            binding.apply {
                binding.news = news
            }
        }
    }

    class NewsViewHolderNoImg(val binding: ItemNewsWithoutImgBinding) : BaseViewHoler(binding) {
        override fun bind(news: Article) {
            binding.apply {
                binding.news = news
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(news : Article)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.listener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHoler {
         when (viewType) {
             IMAGE -> {
                 val itemBinding =
                     ItemNewsBinding.inflate(LayoutInflater.from(context), parent, false)
                 return NewsViewHolder(itemBinding)
             }
             NO_IMAGE -> {
                 val itemBinding =
                     ItemNewsWithoutImgBinding.inflate(LayoutInflater.from(context), parent, false)
                 return NewsViewHolderNoImg(itemBinding)
             }
             else -> {
                 val itemBinding =
                     ItemNewsBinding.inflate(LayoutInflater.from(context), parent, false)
                 return NewsViewHolder(itemBinding)
             }
        }
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    override fun getItemViewType(position: Int): Int {
        val news = listNews.get(position)
        if (news.urlToImage != null) {
            return IMAGE
        } else {
            return NO_IMAGE
        }
    }

    override fun onBindViewHolder(holder: BaseViewHoler, position: Int) {
        val news = listNews.get(position)
        holder.bind(news)
        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                listener?.onClick(news)
            }

        })
    }


}