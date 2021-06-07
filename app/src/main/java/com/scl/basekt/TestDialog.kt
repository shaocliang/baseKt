package com.scl.basekt

import android.view.View
import com.scl.basekt.databinding.DiallogTestBinding
import com.scl.baselibrary.base.BaseDialogFragment

/**
 * Created by admin on 2021/6/7.
 * name:
 */
class TestDialog : BaseDialogFragment() {

    companion object {

        @JvmStatic
        fun newInstance() = TestDialog()
    }

    private val viewBinding by lazy {
        DiallogTestBinding.inflate(layoutInflater)
    }

    override fun attachLayoutView(): View = viewBinding.root

    override fun onInitView() {

    }

    override fun initTheme(): Int = R.style.BottomDialog
}