package com.example.labdiary.uiState

data class AddDisUiState(
    val inputtedName: String = "",
    val inputtedQuantity: Int = 0,
    val isGoingToMain: Boolean = false,
    val isFieldsValid: Boolean = false
)
