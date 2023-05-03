package com.myapplication.di

import com.myapplication.ui.screen.home.HomeViewModel
import com.myapplication.ui.screen.quoteView.QuoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { QuoteViewModel(get()) }
}

