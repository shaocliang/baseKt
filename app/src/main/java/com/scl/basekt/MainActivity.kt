package com.scl.basekt

import android.view.View
import com.scl.basekt.databinding.ActivityMainBinding
import com.scl.baselibrary.base.BaseActivity
import org.greenrobot.eventbus.EventBus

class MainActivity : BaseActivity() {


    private val viewBind by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun attachLayoutView(): View = viewBind.root

    override fun onInitView() {
        viewBind.text.text = "haha"

        viewBind.button.setOnClickListener {
            TestActivity.jumpTo(mContext)
        }
    }

    override fun createObserver() {
    }

    /**
     * 注册注销事件监听.
     */
    override fun registerEventBus(isRegister: Boolean) {
        if (isRegister) {
            /*判断是否注册*/
            if (!EventBus.getDefault().isRegistered(this)) {
                /*没有注册,则注册*/
                EventBus.getDefault().register(this)
            }
        } else {
            if (EventBus.getDefault().isRegistered(this)) {
                /*解绑注册*/
                EventBus.getDefault().unregister(this)
            }
        }
    }


}