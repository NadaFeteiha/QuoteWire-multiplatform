package com.myapplication.di

import com.myapplication.ui.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModules = module {
    viewModel { HomeViewModel(get()) }
}

