package com.xuandq.newsapp.utils

import android.content.SharedPreferences
import android.content.Context

class AppSettings {
    private val sp : SharedPreferences
    private var theme : Int = 0
    constructor(context : Context?){
        sp = context?.getSharedPreferences("prefApp",Context.MODE_PRIVATE)!!
        theme = sp.getInt(THEME_KEY,0)
    }

    companion object{
        const val THEME_KEY = "theme"
        const val THEME_LIGHT = 0
        const val THEME_DARK = 1
    }

    fun getTheme():Int{
        return theme
    }
    fun setTheme(theme:Int){
        this.theme = theme
        val edit = sp.edit()
        edit.putInt(THEME_KEY,theme)
        edit.commit()
    }


}