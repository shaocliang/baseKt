package com.scl.baselibrary.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by admin on 2021/6/7.
 * name:
 */
interface IView {

    /**
     * 设置布局
     */
//    val layoutRoot: View
    fun attachLayoutView() : View

    /**
     * 上下文
     */
    var mContext: AppCompatActivity


    fun onInitView()

    /**
     * 创建LiveData数据观察者
     */
    fun createObserver()
}