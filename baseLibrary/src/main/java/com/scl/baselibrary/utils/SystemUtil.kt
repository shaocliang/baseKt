package com.scl.baselibrary.utils

import android.os.Build

/**
 * Created by scl on 2021/5/12.
 * name:
 */
object SystemUtil {

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    fun getSystemModel(): String? {
        return Build.MODEL
    }


    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    fun getDeviceBrand(): String? {
        return Build.BRAND
    }


    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    fun getSystemVersion(): String? {
        return Build.VERSION.RELEASE
    }

}