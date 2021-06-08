package com.scl.basekt

import android.util.Log
import android.view.View
import com.scl.basekt.databinding.ActivityMainBinding
import com.scl.basekt.test.InfoViewModel
import com.scl.baselibrary.base.BaseActivity
import org.greenrobot.eventbus.EventBus
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {


    private val mViewModel by viewModel<InfoViewModel>()

    private val viewBind by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun attachLayoutView(): View = viewBind.root

    override fun onInitView() {
        viewBind.text.text = "haha"

        viewBind.button.setOnClickListener {
            TestActivity.jumpTo(mContext)
        }

        viewBind.btn1.setOnClickListener {
            TestDialog.newInstance().show(supportFragmentManager)
        }

        viewBind.btn2.setOnClickListener {
            mViewModel.getInfo()
        }
    }

    override fun createObserver() {

        mViewModel.mLoadingViewEvent.observe(this, {
            it.successData?.run {
              Log.d("初始化信息","成功")
            }

            it.showError?.let {
                Log.d("初始化信息","失败")
            }
        })
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