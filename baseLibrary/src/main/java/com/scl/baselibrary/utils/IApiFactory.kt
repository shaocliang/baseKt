package com.scl.baselibrary.utils

/**
 * Created by admin on 2021/6/7.
 * name:切换开发模式
 */
object IApiFactory {

    const val DEFAULT_MAX_CACHE_SIZE = 10 * 1024 * 1024.toLong()
    const val MODE_ON_LINE = 0 //线上
    const val MODE_ON_TEST = 1 //测试
    const val MODE_ON_GRAY = 2 //灰度
}