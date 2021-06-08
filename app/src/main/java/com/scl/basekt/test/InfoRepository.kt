package com.scl.basekt.test

import android.util.Log
import com.scl.basekt.http.WaterRetrofitClient
import com.scl.basekt.http.WaterService
import com.scl.baselibrary.base.BaseModel
import com.scl.baselibrary.base.ResponseResult

/**
 * Created by admin on 2021/6/7.
 * name:
 */
class InfoRepository : BaseModel<WaterService>() {
    private val TAG = "初始化信息"

    override var mApiService: WaterService = WaterRetrofitClient.service

    suspend fun getInfo(): ResponseResult<PersonalCenterInfo> {
        return safeApiCall(call = {
            requestGetInfo()
        }, errorMessage = "")
    }

    private suspend fun requestGetInfo(): ResponseResult<PersonalCenterInfo> {
        val response = mApiService.getInfo()
        return executeResponse(response,
            { Log.d(TAG, "成功$response") },
            { Log.d(TAG, "失败$response") })
    }
}