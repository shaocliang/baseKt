package com.scl.basekt.http

import com.scl.baselibrary.base.IResponseEntity

/**
 * Created by admin on 2021/6/7.
 * name:响应结果实体
 */
data class BaseResponse<T>(
    var code: Int = -1, val msg: String, val data: T
) : IResponseEntity<T> {

    val message: String = msg

    /*处理空数据*/
//    fun getEntity(): T = data

    override fun succeed(): Boolean = code == 0

    override fun code(): Int = code

    override fun entity(): T? = data

    override fun message(): String = msg

}
