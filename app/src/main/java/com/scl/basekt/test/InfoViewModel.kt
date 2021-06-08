package com.scl.basekt.test

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scl.baselibrary.base.BaseViewModel
import com.scl.baselibrary.base.LoadingModel
import com.scl.baselibrary.base.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by admin on 2021/6/7.
 * name:
 */
class InfoViewModel : BaseViewModel<InfoRepository>() {

    val mLoadingViewEvent = MutableLiveData<LoadingModel<PersonalCenterInfo?>>()

    fun getInfo() {
        viewModelScope.launch(Dispatchers.Main) {

            val result = withContext(Dispatchers.IO) {
                mModel?.getInfo()
            }

            if (result is ResponseResult.Success) {
                Log.d("初始化信息", result.data.toString())

                emitLoading(successData = result.data).run {
                    mLoadingViewEvent.postValue(this)
                }


            } else if (result is ResponseResult.Error) {
                Log.d("初始化信息", result.errorMsg)
                emitLoading<PersonalCenterInfo>(showError = result.errorMsg).run {
                    mLoadingViewEvent.postValue(this)
                }
            }

        }
    }
}