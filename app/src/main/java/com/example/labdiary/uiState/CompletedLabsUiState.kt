package com.example.labdiary.uiState

data class CompletedLabsUiState(
    val labsInfo: ArrayList<Pair<String/*описание*/, String/*дата*/>> = arrayListOf(),
    val picked: Int = -1,
    val isConfirmWindowShowing: Boolean = false
)
