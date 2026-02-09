package com.example.springvalidation.presentation.screens.start_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.springvalidation.domain.AppStateRepository
import kotlinx.coroutines.launch

class StartViewModel(
    private val repository: AppStateRepository
) : ViewModel() {

    fun onStartNewMorning() {
        viewModelScope.launch {
            repository.setNewMorningStarted()
        }
    }
}
