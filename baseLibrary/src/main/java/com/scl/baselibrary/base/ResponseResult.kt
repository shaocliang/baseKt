package com.scl.baselibrary.base

/**
 * Created by admin on 2021/6/7.
 * name:请求结果
 */
sealed class ResponseResult<out T : Any> {

    /**
     * 请求成果的数据解析结果,data字段泛型实体,multilayer.
     */
    data class Success<T : Any>(val data: T) : ResponseResult<T>()

    /**
     * 请求成功,用于解析单层json结果的无泛型实体嵌套.monolayer.
     */
    data class BaseSuccess(val data: IResponseEntity<*>) : ResponseResult<Nothing>()

    /**
     * 请求失败,回调提示错误信息.
     */
    data class Error(val errorMsg: String) : ResponseResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is BaseSuccess -> "Success[data=$data]"
            is Error -> "Error[errorMsg=$errorMsg]"
        }
    }
}