package com.example.springvalidation.di

import com.example.springvalidation.data.datastore.CapturedPhotoStorage
import com.example.springvalidation.presentation.FirstMomentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import com.example.springvalidation.data.datastore.appDataStore


val appModule = module {

    // DataStore
    single {
        androidContext().appDataStore
    }

    single {
        CapturedPhotoStorage(dataStore = get())
    }

    viewModel {
        FirstMomentViewModel(capturedPhotoStorage = get())
    }
}