package com.example.springvalidation.di

import com.example.springvalidation.data.AppStateRepository
import com.example.springvalidation.presentation.screens.start_screen.StartViewModel
import com.example.springvalidation.presentation.startup.AppStartViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppStateRepository(androidContext()) }
    
    viewModel { AppStartViewModel(get()) }
    viewModel { StartViewModel(get()) }
}
