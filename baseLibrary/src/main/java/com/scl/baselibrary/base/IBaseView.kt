package com.scl.baselibrary.base

import android.view.View

/**
 * Created by admin on 2021/6/7.
 * name:
 */
interface IBaseView {

    /**
     * 标题.
     */
    var titleBar: View?

    /**
     * 是否使用沉浸式
     */
    var usedImmersionBar: Boolean

    /**
     * 是否使用EventBus
     */
    var usedEventBus: Boolean


    /**
     * 是否启用白色状态栏,黑色字体.
     */
    var usedStatusBarDarkFont: Boolean

    /**
     * 控制注册注销使用EventBus
     */
    fun registerEventBus(isRegister: Boolean)

    /**
     * 沉浸式状态栏
     */
    fun onInitImmersionBar()

    /**
     * 吐司
     */
    fun showToast(msg: String)


}