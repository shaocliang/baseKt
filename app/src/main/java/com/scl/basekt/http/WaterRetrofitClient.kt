package com.scl.basekt.http

import com.scl.basekt.utils.NetWorkUtils
import com.scl.baselibrary.utils.IApiFactory
import com.scl.baselibrary.utils.UIUtils
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import java.io.File

/**
 * Created by admin on 2021/6/7.
 * name:Retrofit请求的单例.
 */
object WaterRetrofitClient : BaseRetrofitClient() {

    val service by lazy {
        getService(
            WaterService::class.java,
            baseUrl()
        )
    }

    private var isApkInDebug = true


    override fun handleBuilder(builder: OkHttpClient.Builder) {

        if (isApkInDebug) {
            builder.addInterceptor(LogInterceptor())
        }
        val httpCacheDirectory = File(UIUtils.getContext().cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024L // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)
        //应用拦截器：设置缓存策略
        builder.cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()
                if (!NetWorkUtils.isNetworkAvailable(UIUtils.getContext())) { //没有网络的时候使用缓存
                    request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
                }

                val response = chain.proceed(request)
                if (!NetWorkUtils.isNetworkAvailable(UIUtils.getContext())) {
                    val maxAge = 60 * 60
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=$maxAge")
                        .build()

                } else {
                    val maxStale = 60 * 60 * 24 * 28
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .build()
                }
                response// 疑问：为什么要添加这个调用 还没有理解
            }
    }

    /*修改环境*/
    var mMode = IApiFactory.MODE_ON_LINE

    private fun baseUrl(): String = when (mMode) {
        IApiFactory.MODE_ON_LINE -> WaterService.URL_PRODUCE
        IApiFactory.MODE_ON_TEST -> WaterService.URL_TEST
        IApiFactory.MODE_ON_GRAY -> WaterService.URL_DEV
        else -> WaterService.URL_PRODUCE
    }
}