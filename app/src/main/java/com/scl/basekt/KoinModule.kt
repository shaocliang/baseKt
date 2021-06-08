package com.scl.basekt

import com.scl.basekt.test.InfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by admin on 2021/6/8.
 * name:
 */

val viewModelModule = module {
    viewModel { InfoViewModel() }

}

val repositoryModule = module {

}

val appModule = listOf(viewModelModule, repositoryModule)