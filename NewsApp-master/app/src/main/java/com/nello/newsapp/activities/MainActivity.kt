package com.xuandq.newsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xuandq.newsapp.R
import com.xuandq.newsapp.utils.AppSettings

class MainActivity : AppCompatActivity() {
    private lateinit var nvController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyTheme()
        setContentView(R.layout.activity_main)
        setUpBottomNav()

    }

    private fun setUpBottomNav() {
        val bottomNav : BottomNavigationView = findViewById(R.id.bottomNavigationView)
        nvController = findNavController(R.id.nav_host_fragment)
        bottomNav.setupWithNavController(nvController)
    }

    private fun applyTheme(){
        val setting = AppSettings(applicationContext)
        when (setting.getTheme()){
            AppSettings.THEME_LIGHT -> setTheme(R.style.AppTheme)
            AppSettings.THEME_DARK -> setTheme(R.style.DarkTheme)
            else -> setTheme(R.style.AppTheme)
        }
    }
}