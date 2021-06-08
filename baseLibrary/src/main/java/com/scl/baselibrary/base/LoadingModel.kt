package com.scl.baselibrary.base

/**
 * Created by admin on 2021/6/7.
 * name:
 */
data class LoadingModel<T>(
    val successData: T? = null,
    val showError: String? = null
)
