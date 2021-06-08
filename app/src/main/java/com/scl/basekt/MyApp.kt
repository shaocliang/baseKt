package com.scl.basekt

import android.app.Application
import com.scl.baselibrary.BaseLib
import com.scl.baselibrary.utils.UIUtils
import org.koin.core.context.startKoin

/**
 * Created by admin on 2021/5/12.
 * name:
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        BaseLib.init(this)
        UIUtils.init(this)

        startKoin {
            modules(appModule)
        }
    }
}