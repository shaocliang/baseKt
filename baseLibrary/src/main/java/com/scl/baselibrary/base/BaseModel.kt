package com.scl.baselibrary.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 * Created by admin on 2021/6/7.
 * name:
 */
abstract class BaseModel<S> : IBaseModel<S> {

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> ResponseResult<T>,
        errorMessage: String
    ): ResponseResult<T> {

        return try {
            call()
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseResult.Error(errorMessage)
        }
    }

    /**
     * 解析响应结果.
     */
    open suspend fun <T : Any> executeResponse(response: IResponseEntity<T>,
                                               successBlock: (suspend CoroutineScope.() -> Unit)? = null,
                                               errorBlock: (suspend CoroutineScope.() -> Unit)? = null): ResponseResult<T> {
        return coroutineScope {
            if (response.succeed()) {
                successBlock?.let { it() }
                if (response.entity() != null) {
                    ResponseResult.Success(response.entity()!!)
                } else {
                    ResponseResult.BaseSuccess(response)
                }
            } else {
                errorBlock?.let { it() }
                ResponseResult.Error(response.message())
            }
        }
    }
}