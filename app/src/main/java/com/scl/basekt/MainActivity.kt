package com.scl.basekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scl.baselibrary.utils.SharedPreferencesData
import com.scl.baselibrary.utils.SystemUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SystemUtil.getDeviceBrand()

        SharedPreferencesData.name = "测试"
    }
}