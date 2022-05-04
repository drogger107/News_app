package com.xuandq.newsapp.utils

import android.text.format.DateUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.xuandq.newsapp.R
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("glide_url")
    fun loadImg(img: ImageView, url: String?) {
        if (url != null) Glide.with(img.context).load(url).into(img)
        else {
            img.setImageResource(R.mipmap.ic_launcher)
        }
    }
    @JvmStatic
    @BindingAdapter("setDateFormatted")
    fun loadDateFormatted(txt: TextView, s: String?) {
        if (s == null) {
            txt.setText("No Date")
        } else {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            var date = sdf.parse(s)
            var newDate = SimpleDateFormat("d MMM yyyy", Locale.ENGLISH).format(date)
            txt.setText(newDate.toString())
        }
    }
    @JvmStatic
    @BindingAdapter("setTimeFormatted")
    fun loadTimeFormatted(txt: TextView, s : String? ){
        if (s==null){
            txt.setText("No Time ")
        }else {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            var timeMillis = sdf.parse(s).time
            // GMT+8
            val span =
                Math.max(System.currentTimeMillis() - timeMillis - 8 * DateUtils.HOUR_IN_MILLIS, 0)
            if (span >= DateUtils.YEAR_IN_MILLIS) {
                txt.text = (span / DateUtils.YEAR_IN_MILLIS).toString() + " years ago"
                return
            }
            if (span >= DateUtils.WEEK_IN_MILLIS) {
                txt.text = (span / DateUtils.WEEK_IN_MILLIS).toString() + " weeks ago"
                return
            }
            if (span >= DateUtils.DAY_IN_MILLIS) {
                txt.text =  (span / DateUtils.DAY_IN_MILLIS).toString() +  " days ago"
                return
            }
            if (span >= DateUtils.HOUR_IN_MILLIS) {
                txt.text = (span / DateUtils.HOUR_IN_MILLIS).toString() + " hours ago"
            } else {
                txt.text = (span / DateUtils.MINUTE_IN_MILLIS).toString() + " minutes ago"
            }
        }
    }

}