package com.example.springvalidation.presentation.startup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.springvalidation.data.AppStateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AppStartViewModel(
    private val repository: AppStateRepository
) : ViewModel() {

    sealed interface StartDestination {
        object Loading : StartDestination
        object Start : StartDestination
        object FreshStart : StartDestination
    }

    private val _startDestination =
        MutableStateFlow<StartDestination>(StartDestination.Loading)

    val startDestination: StateFlow<StartDestination> = _startDestination

    init {
        viewModelScope.launch {
            val hasStarted = repository.hasStartedNewMorning.first()
            _startDestination.value =
                if (hasStarted) {
                    StartDestination.FreshStart
                } else {
                    StartDestination.Start
                }
        }
    }
}
