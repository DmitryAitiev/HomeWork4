package com.example.homework4.ui.theme

import com.example.homework4.PersonInfo


sealed class UIState {

    data object Loading: UIState()
    data class Success(val data: PersonInfo): UIState()
    data class Error(val message: String): UIState()
}