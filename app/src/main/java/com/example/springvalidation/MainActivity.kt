package com.example.springvalidation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.springvalidation.presentation.NewChapterScreenRoot
import com.example.springvalidation.ui.theme.SpringValidationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpringValidationTheme {
                NewChapterScreenRoot()
            }
        }
    }
}