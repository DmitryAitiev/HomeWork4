package com.example.homework4.ui.theme

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homework4.MainViewModel
import com.example.homework4.R

@Composable
fun MainScreen() {

    val viewModel: MainViewModel = viewModel()
    Column(modifier = Modifier.fillMaxSize()) {

        val uiState = viewModel.uiState.collectAsState()

        val progress = when (uiState.value) {
            UIState.Loading -> 0.3f
            is UIState.Success -> 0.66f
            is UIState.Error -> 1f
        }
        val animatedProgress by animateFloatAsState(
            targetValue = progress,
            animationSpec = tween(1000), label = ""
        )

        LinearProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp),
        )

        when (val state = uiState.value) {
            UIState.Loading -> LoadingScreen()
            is UIState.Error -> ErrorScreen(state.message) { viewModel.resetError() }
            is UIState.Success -> {
                val info = state.data
                SuccessScreen(
                    personalInfo = info,
                    onFirstNameChange = {
                        viewModel.setFirstName(it)
                    },
                    onLastNameChange = {
                        viewModel.setLastName(it)
                    },
                    onDateOfBirthChange = {
                        viewModel.setDateOfBirth(it)
                    },
                    onSexChange = {
                        viewModel.setSex(it)
                    },
                    onTriggerError = viewModel::triggerError
                )
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(errorMessage: String, onRetry: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Error: $errorMessage")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onRetry) {
                    Text(stringResource(R.string.back_text))
                }
            }
        }
    }
}