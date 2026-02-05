package com.example.springvalidation.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun OnScreenResume(
    key1: Any? = null,
    key2: Any? = null,
    onResume: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val latestOnResume = rememberUpdatedState(onResume)

    DisposableEffect(lifecycleOwner.lifecycle, key1, key2) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                latestOnResume.value()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
