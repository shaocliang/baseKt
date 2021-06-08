package com.scl.baselibrary.base

import androidx.lifecycle.ViewModel
import java.lang.reflect.ParameterizedType

/**
 * Created by admin on 2021/6/7.
 * name:BaseViewModel
 */
abstract class BaseViewModel<M : IBaseModel<*>> : ViewModel() {


    @JvmField
    protected var mModel: M? = null

    init {
        mModel = getNewInstance(this, 0)
    }

    /**
     *发射器
     *
     * @param successData  给Ui的数据
     * @param showError  错误信息
     */
    fun <T> emitLoading(
        successData: T? = null,
        showError: String? = null
    ): LoadingModel<T?> = LoadingModel(successData, showError)


    private fun <M> getNewInstance(obj: Any?, i: Int): M? {
        if (obj != null) {
            try {
                return ((obj.javaClass
                    .genericSuperclass as ParameterizedType).actualTypeArguments[i] as Class<M>)
                    .newInstance()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return null
    }
}