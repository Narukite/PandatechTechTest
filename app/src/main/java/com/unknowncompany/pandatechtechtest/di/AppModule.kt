package com.unknowncompany.pandatechtechtest.di

import com.unknowncompany.pandatechtechtest.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
}