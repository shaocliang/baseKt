package com.scl.baselibrary.base

import android.content.Context
import android.content.Intent

/**
 * Created by admin on 2021/6/7.
 * name:
 */


/**
 *  跳转Activity方法.
 *  @param context
 *  @param block 函数lamada.
 */
inline fun <reified T> jumpInTo(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}

/**
 *  跳转Activity方法.
 *  @param context
 */
inline fun <reified T> jumpInTo(context: Context) {
    val intent = Intent(context, T::class.java)
    context.startActivity(intent)
}