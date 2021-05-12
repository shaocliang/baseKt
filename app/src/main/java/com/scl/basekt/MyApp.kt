package com.scl.basekt

import android.app.Application
import com.scl.baselibrary.BaseLib

/**
 * Created by admin on 2021/5/12.
 * name:
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        BaseLib.init(this)
    }
}