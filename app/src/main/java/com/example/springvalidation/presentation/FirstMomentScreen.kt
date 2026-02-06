package com.example.springvalidation.presentation

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.springvalidation.R
import com.example.springvalidation.presentation.components.CameraPermissionPrompt
import com.example.springvalidation.presentation.components.CapturePrompt
import com.example.springvalidation.presentation.components.CapturedPhoto
import com.example.springvalidation.presentation.interaction.FirstMomentUiAction
import com.example.springvalidation.presentation.interaction.FirstMomentUiEvent
import com.example.springvalidation.presentation.interaction.FirstMomentUiState
import com.example.springvalidation.presentation.interaction.FirstMomentUiState.ScreenState
import com.example.springvalidation.presentation.media.CameraImageStore
import com.example.springvalidation.presentation.permission.CameraPermissionResolver
import com.example.springvalidation.presentation.permission.CameraPermissionState
import com.example.springvalidation.ui.common_components.PrimaryButton
import com.example.springvalidation.ui.common_components.SpringValidationScaffold
import com.example.springvalidation.ui.util.ObserveAsEvents
import com.example.springvalidation.ui.util.OnScreenResume

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewChapterScreenRoot() {
    val viewModel: FirstMomentViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var pendingImageUri by remember { mutableStateOf<Uri?>(null) }

    OnScreenResume {
        val currentPermission = CameraPermissionResolver.readCurrentState(context)

        val shouldSyncPermission =
            currentPermission == CameraPermissionState.Granted &&
            uiState.permission != CameraPermissionState.Granted

        if (shouldSyncPermission) {
            viewModel.onAction(
                FirstMomentUiAction.OnPermissionSynced(currentPermission)
            )
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        val activity = context as? Activity

        val permissionState = CameraPermissionResolver.resolve(isGranted, activity)

        viewModel.onAction(FirstMomentUiAction.OnPermissionResult(permissionState))
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        val uri = pendingImageUri

        if (success && uri != null) {
            viewModel.onAction(
                FirstMomentUiAction.OnPhotoCaptured(uri)
            )
        } else if (uri != null) {
            CameraImageStore.deleteImage(context, uri)
        }

        pendingImageUri = null
    }

    ObserveAsEvents(viewModel.events) { events ->
        when (events) {
            FirstMomentUiEvent.OpenCamera -> {
                val imageUri = CameraImageStore.createImageUri(context)
                pendingImageUri = imageUri
                cameraLauncher.launch(imageUri)
            }

            FirstMomentUiEvent.RequestCameraPermission -> {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }

            FirstMomentUiEvent.OpenAppSettings -> {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
                context.startActivity(intent)
            }
        }
    }

    NewChapterScreen(
        uiState = uiState,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewChapterScreen(
    uiState: FirstMomentUiState,
    onAction: (FirstMomentUiAction) -> Unit
) {

    SpringValidationScaffold(
        title = stringResource(R.string.first_spring_moment),
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            val buttonText = when (uiState.screenState) {
                is ScreenState.Captured -> stringResource(R.string.update_moment)
                ScreenState.Initial -> stringResource(R.string.take_spring_photo)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                when (uiState.screenState) {
                    is ScreenState.Captured -> {
                        CapturedPhoto(
                            photoUri = uiState.screenState.photo,
                            modifier = Modifier.align(Alignment.TopCenter)
                        )
                    }
                    ScreenState.Initial -> CapturePrompt(modifier = Modifier.align(Alignment.Center))
                }
            }

            PrimaryButton(
                text = buttonText,
                onClick = { onAction(FirstMomentUiAction.OnPrimaryButtonClicked) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    if (uiState.showPermissionExplanationDialog) {
        ModalBottomSheet(
            onDismissRequest = {
                onAction(FirstMomentUiAction.OnCancelDialog)
            },
            sheetState = rememberModalBottomSheetState(),
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            CameraPermissionPrompt(
                onCancel = {
                    onAction(FirstMomentUiAction.OnCancelDialog)
                },
                onOpenSettings = {
                    onAction(FirstMomentUiAction.OnOpenSettings)
                }
            )
        }
    }
}

//@Preview(
//    showBackground = true
//)
//@Composable
//fun NewChapterScreenPreview() {
//    SpringValidationTheme {
//        NewChapterScreen(
//            uiState = FirstMomentUiState(),
//            onAction = {}
//        )
//    }
//}