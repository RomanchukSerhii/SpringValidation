package com.example.springvalidation.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.springvalidation.data.datastore.DataStoreCapturedPhotoStorage
import com.example.springvalidation.presentation.FirstMomentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import com.example.springvalidation.data.datastore.appDataStore
import com.example.springvalidation.domain.CapturedPhotoStorage


val appModule = module {

    single<DataStore<Preferences>> {
        androidContext().appDataStore
    }

    single<CapturedPhotoStorage> {
        DataStoreCapturedPhotoStorage(dataStore = get())
    }

    viewModel {
        FirstMomentViewModel(capturedPhotoStorage = get())
    }
}