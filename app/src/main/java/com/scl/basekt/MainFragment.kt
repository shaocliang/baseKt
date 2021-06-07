package com.scl.basekt

import android.view.View
import com.scl.basekt.databinding.FragmentMainBinding
import com.scl.baselibrary.base.BaseFragment
import org.greenrobot.eventbus.EventBus

/**
 * Created by admin on 2021/6/7.
 * name:
 */
class MainFragment : BaseFragment() {

    private val viewBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    override fun lazyLoadData() {
    }

    override fun attachLayoutView(): View = viewBinding.root

    override fun onInitView() {
        viewBinding.fT.text = "测试"
    }

    override fun createObserver() {
    }

    /*重写
    * 参考 MainActivity
    * */
    override fun registerEventBus(isRegister: Boolean) {
        if (isRegister) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this)
            }
        } else {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this)
            }
        }
    }

    override fun onLoading(isLoading: Boolean) {
    }
}