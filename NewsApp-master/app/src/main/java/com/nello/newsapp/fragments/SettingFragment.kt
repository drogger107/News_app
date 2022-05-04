package com.xuandq.newsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.xuandq.newsapp.R
import com.xuandq.newsapp.utils.AppSettings

class SettingFragment : Fragment() {
    private lateinit var rbGroup : RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rbGroup = view.findViewById(R.id.setting_rb_group)
        val setting = AppSettings(requireContext())
        when (setting.getTheme()){
            AppSettings.THEME_LIGHT -> rbGroup.check(R.id.setting_rb_light)
            AppSettings.THEME_DARK -> rbGroup.check(R.id.setting_rb_dark)
            else -> rbGroup.check(R.id.setting_rb_light)
        }
        rbGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            when (radioGroup.checkedRadioButtonId){
                R.id.setting_rb_light -> setting.setTheme(AppSettings.THEME_LIGHT)
                R.id.setting_rb_dark -> setting.setTheme(AppSettings.THEME_DARK)
                else -> setting.setTheme(AppSettings.THEME_LIGHT)
            }
        })
    }
}