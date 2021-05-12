package com.scl.baselibrary

import android.content.Context

/**
 * Created by admin on 2021/5/12.
 * name:
 */
class BaseLib private constructor(){

    companion object {
        var mContext: Context? = null

        fun init(context: Context) {
            if (context != null) {
                mContext = context
            }
        }

        fun getContext() : Context {
            return mContext!!
        }
    }


}