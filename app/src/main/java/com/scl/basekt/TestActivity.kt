package com.scl.basekt

import android.content.Context
import android.view.View
import com.scl.basekt.databinding.ActivityTestBinding
import com.scl.baselibrary.base.BaseActivity
import com.scl.baselibrary.base.jumpInTo

/**
 * Created by admin on 2021/6/7.
 * name:
 */
class TestActivity : BaseActivity() {

    companion object {
        @JvmStatic
        fun jumpTo(context: Context) {
            jumpInTo<TestActivity>(context)
        }
    }

    private val viewBinding by lazy {
        ActivityTestBinding.inflate(layoutInflater)
    }

    override fun attachLayoutView(): View = viewBinding.root

    override fun onInitView() {

    }

    override fun createObserver() {
    }

    override fun registerEventBus(isRegister: Boolean) {
    }
}