package com.scl.baselibrary.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gyf.immersionbar.ImmersionBar

/**
 * Created by admin on 2021/6/7.
 * name:
 */
abstract class BaseFragment : Fragment(), IView, IBaseView, ILoadingView {

    //是否第一次加载
    var isFirst: Boolean = true
        private set

    override var titleBar: View? = null
    override var usedImmersionBar: Boolean = false
    override var usedStatusBarDarkFont: Boolean = false
    override var usedEventBus: Boolean = false
    override lateinit var mContext: AppCompatActivity
    override var cancelable: Boolean = true


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as AppCompatActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return attachLayoutView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isFirst = true

        createObserver()

        onInitView()

        initData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (usedEventBus) registerEventBus(true)

//        onInitImmersionBar()
    }


    override fun onDestroy() {
        registerEventBus(false)
        super.onDestroy()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        view?.post {
            if (!hidden) {
                lazyLoadData()
            }
        }
    }


    override fun onInitImmersionBar() {
//        if (usedImmersionBar) {
//            if (usedStatusBarDarkFont) {
//                setStatusBarDarkFont()
//            } else {
//                ImmersionBar.with(this)
//                    .titleBar(titleBar)
//                    .init()
//            }
//        }
    }

//    /**
//     * 白色状态栏,黑色字体,黑色导航栏,解决了白色状态栏无法看见状态栏字体颜色问题
//     */
//    open fun setStatusBarDarkFont() {
//        ImmersionBar.with(this)
//            .statusBarDarkFont(true)
//            .navigationBarDarkIcon(true)
//            .titleBar(titleBar)
//            .init()
//    }


    override fun showToast(msg: String) {
        if (activity is BaseActivity/*<*>*/) {
            val activity = activity as BaseActivity/*<*>?*/
            activity.showToast(msg)
        }
    }

    override fun showLoading(msg: String?) {
        if (activity is BaseActivity/*<*>*/) {
            val activity = activity as BaseActivity/*<*>?*/
            activity.showLoading(msg)
        }
    }

    override fun showLoading(msgResId: Int) {
        if (activity is BaseActivity/*<*>*/) {
            val activity = activity as BaseActivity/*<*>?*/
            activity.showLoading(msgResId)
        }
    }

    override fun closeLoading() {
        if (activity is BaseActivity/*<*>*/) {
            val activity = activity as BaseActivity/*<*>?*/
            activity.closeLoading()
        }
    }


    /**
     * Fragment执行onCreate后触发的方法
     */
    open fun initData() {}

    /**
     * 当可见的时候加载数据.
     */
    abstract fun lazyLoadData()
}