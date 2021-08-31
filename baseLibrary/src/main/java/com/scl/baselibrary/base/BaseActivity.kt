package com.scl.baselibrary.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import com.scl.baselibrary.R

/**
 * Created by admin on 2021/6/7.
 * name:
 */
abstract class BaseActivity : AppCompatActivity(), IView, IBaseView, ILoadingView {

    /*上下文*/
    override lateinit var mContext: AppCompatActivity

    /*是否开启沉浸式状态栏*/
    override var usedImmersionBar: Boolean = false

    /*是否是白色状态栏*/
    override var usedStatusBarDarkFont: Boolean = false

    /*自定义titleBar的View*/
    override var titleBar: View? = null

    /*是否开启本页面 eventBus*/
    override var usedEventBus: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this

        setContentView(attachLayoutView())

        if (usedEventBus) {
            registerEventBus(true)
        }

        createObserver()

        onInitView()

        onInitImmersionBar()
    }

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    override fun onInitImmersionBar() {
        if (usedImmersionBar) {
            if (usedStatusBarDarkFont) {
                setStatusBarDarkFont()
            } else {
                ImmersionBar.with(this)
                    .titleBar(titleBar)
                    .init()
            }
        }
    }

    /**
     * 白色状态栏,黑色字体,黑色导航栏,解决了白色状态栏无法看见状态栏字体颜色问题
     * 详情 : https://github.com/gyf-dev/ImmersionBar
     */
    open fun setStatusBarDarkFont() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .navigationBarDarkIcon(true)
            .titleBar(titleBar)
            .init()
    }

    override fun onDestroy() {
        registerEventBus(false)
        super.onDestroy()
    }

    override fun finish() {
        super.finish()
        startAnimation(false)
    }

    /**
     * 启动页面
     *
     * @param intent      启动意图
     * @param requestCode 请求码
     */
    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        // 返回桌面的时候不要动画,
        if (intent.categories == null || !intent.categories.toString().contains("HOME")) {
            startAnimation(true)
        }
    }

    /**
     * 指定开启动画或者关闭动画
     *
     * @param start true:执行开启动画,false:执行关闭动画.
     */
    open fun startAnimation(start: Boolean) {
        if (usedAnimation()) {
            if (start) {
                //启动动画
                overridePendingTransition(R.anim.right_in, R.anim.left_out)
            } else {
                //关闭动画
                overridePendingTransition(R.anim.left_in, R.anim.right_out)
            }
        }
    }

    /**
     * 是否启动动画,默认启用动画.
     *
     * @return true:启用,false,不启用.
     */
    open fun usedAnimation(): Boolean = true

    /**
     * 自定义吐司,需要重写这个方法
     */
    override fun showToast(msg: String) {}

    /**
     * 自定义开启加载框
     *  只需要在baseActivity重写即可 不需要BaseFragment中重写
     */
    override fun showLoading(msg: String?) {}

    override fun showLoading(msgResId: Int) {}

    /**
     * 自定义关闭加载框
     *  只需要在baseActivity重写即可 不需要BaseFragment中重写
     */
    override fun closeLoading() {}

    /*无用*/
    override fun onLoading(isLoading: Boolean) {
        if (isLoading) {
            showLoading()
        } else {
            closeLoading()
        }
    }

}