package com.scl.baselibrary.utils

import android.content.Context

/**
 * Created by admin on 2021/6/7.
 * name:尺寸转换工具类.
 */
object SizeUtils {

    /**
     * dp转px
     *
     * @param context context
     * @param dpValue dp值
     * @return px值
     */
    @JvmStatic
    fun dp2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * px转dp
     *
     * @param context context
     * @param pxValue px值
     * @return dp值
     */
    @JvmStatic
    fun px2dp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * sp转px
     *
     * @param context context
     * @param spValue sp值
     * @return px值
     */
    @JvmStatic
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * px转sp
     *
     * @param context context
     * @param pxValue px值
     * @return sp值
     */
    @JvmStatic
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }
}