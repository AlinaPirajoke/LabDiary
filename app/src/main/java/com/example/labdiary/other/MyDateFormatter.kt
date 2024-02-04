package com.example.labdiary.other

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object MyDateFormatter {
    private val longFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru"))
    private val shortFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    fun formatToLong(date: LocalDate): String = date.format(longFormatter)
    fun formatToShort(date: LocalDate): String = date.format(shortFormatter)
    fun formatFromShortToLong(date: String): String = LocalDate.parse(date).format(longFormatter)
}