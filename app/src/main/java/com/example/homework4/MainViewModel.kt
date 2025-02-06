package com.example.homework4

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework4.ui.theme.UIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState.asStateFlow()

    private var personInfo by mutableStateOf(PersonInfo())
        private set

    init {
        viewModelScope.launch {
            delay(2000)
            _uiState.value = UIState.Success(personInfo)
        }
    }

    fun setFirstName(value: String) {
        personInfo = personInfo.copy(firstName = value)
        refreshSuccessState()
    }

    fun setLastName(value: String) {
        personInfo = personInfo.copy(lastName = value)
        refreshSuccessState()
    }

    fun setDateOfBirth(value: String) {
        personInfo = personInfo.copy(dateOfBirth = value)
        refreshSuccessState()
    }

    fun setSex(value: String) {
        personInfo = personInfo.copy(selectedSex = value)
        refreshSuccessState()
    }

    private fun refreshSuccessState() {
        val current = _uiState.value
        if (current is UIState.Success) {
            _uiState.value = current.copy(data = personInfo)
        }
    }

    fun triggerError() {
        _uiState.value = UIState.Error("Убийственная ошибка")
    }

    fun resetError() {
        _uiState.value = UIState.Success(personInfo)
    }
}