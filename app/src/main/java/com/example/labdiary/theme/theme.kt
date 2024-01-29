package com.example.labdiary.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun LabDiaryCustomTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = redColors,
        typography = labDiaryTypography,
        shapes = labDiaryShapes,
        content = content
    )
}