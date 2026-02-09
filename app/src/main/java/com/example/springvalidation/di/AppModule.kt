package com.example.springvalidation.di


import com.example.springvalidation.data.DataStoreDraftStorage
import com.example.springvalidation.domain.DraftStorage
import com.example.springvalidation.presentation.screens.NewNoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<DraftStorage> { DataStoreDraftStorage(androidContext()) }

    viewModel { NewNoteViewModel(get()) }
}
