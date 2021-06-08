package com.scl.basekt.http

import com.scl.basekt.test.PersonalCenterInfo
import retrofit2.http.GET

/**
 * Created by admin on 2021/6/7.
 * name:api接口.
 */
interface WaterService {

    companion object {

        /**
         * 开发环境
         */
        const val URL_DEV = "http://172.16.81.50:8008"

        /**
         * 测试
         */
        const val URL_TEST = "http://cs.fish.hcstzz.com"

        /**
         * 正式
         */
        const val URL_PRODUCE = "https://api.fish.hcstzz.com"
    }

    @GET("/api/sysConfig/app/getPersonalCenter")
    suspend fun getInfo(): BaseResponse<PersonalCenterInfo>


}