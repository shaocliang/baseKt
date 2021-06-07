package com.scl.baselibrary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.scl.baselibrary.R

/**
 * Created by admin on 2021/6/7.
 * name:
 */
abstract class BaseDialogFragment : AppCompatDialogFragment(), IView {

    companion object {
        private const val TAG = "BaseDialogFragment"
    }

    override lateinit var mContext: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, initTheme())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mContext = activity as AppCompatActivity

        createObserver()

        onInitView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return attachLayoutView()
    }

    override fun createObserver() {

    }

    /**
     * 重写此方法可以更换主题
     *
     * @return
     */
    open fun initTheme(): Int {
        return R.style.BaseDialog_Nice
    }

    fun show(manager: FragmentManager) {
        super.show(manager, TAG)
    }

    fun showNow(manager: FragmentManager) {
        super.showNow(manager, TAG)
    }

    fun show(transaction: FragmentTransaction?): Int {
        return super.show(transaction!!, TAG)
    }

    fun showToast(msg: String) {
        if (activity is BaseActivity) {
            val activity = activity as BaseActivity
            activity.showToast(msg)
        }
    }

}