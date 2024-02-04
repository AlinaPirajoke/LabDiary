package com.example.labdiary.theme

import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color

val primary = Color(0xFFFFAB40)
val black = Color(0xFF101010)
val lightBlack = Color(0xFF1B1B1B)
val white = Color(0xFFFEFAFD)

val redColors = darkColors(
    primary = primary,
    primaryVariant = primary,
    background = black,
    onBackground = white,
    surface = lightBlack,
    onSurface = white
)