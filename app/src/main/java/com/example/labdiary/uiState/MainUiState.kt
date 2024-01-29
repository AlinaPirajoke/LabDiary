package com.example.labdiary.uiState

data class MainUiState(
    val todayDate: String = "",
    val doneUntil: String = "", // Дата, до которой выполнены лабы (трудно обьяснить)
    val curSemester: Int = 0,
    val resultGrade: Int = 3
//    val labsQuantity: Int = 0,
)