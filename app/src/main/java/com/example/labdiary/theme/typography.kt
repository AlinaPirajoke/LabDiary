package com.example.labdiary.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.labdiary.R

val cocon_regular = Font(R.font.cocon_regular, FontWeight.W600)
val Cocon = FontFamily(cocon_regular)

val labDiaryTypography = Typography(
    h3 = TextStyle(
        fontFamily = Cocon,
        fontWeight = FontWeight.W600,
        fontSize = 40.sp
    ),

    h4 = TextStyle(
        fontFamily = Cocon,
        fontWeight = FontWeight.W600,
        fontSize = 32.sp
    ),

    h5 = TextStyle(
        fontFamily = Cocon,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),

    h6 = TextStyle(
        fontFamily = Cocon,
        fontWeight = FontWeight.W400,
        fontSize = 20.sp
    ),

    subtitle1 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    ),

    subtitle2 = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 18.sp
    ),

    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp
    ),

    body2 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 17.sp
    ),

    button = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 15.sp
    ),

    caption = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 15.sp
    ),

    overline = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    )
)