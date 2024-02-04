package com.example.labdiary.uiState

data class AddLabUiState(
    val pickedLab: Int = -1,
    val labList: ArrayList<String> = arrayListOf(),
    val isGoingToMain: Boolean = false
)
