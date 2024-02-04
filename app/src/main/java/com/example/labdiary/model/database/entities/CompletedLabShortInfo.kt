package com.example.labdiary.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.sql.Date

@Entity
data class CompletedLabShortInfo (
    @ColumnInfo(name = "id") val id: Int = -1,
    @ColumnInfo(name = "number") val number: Int = -1,
    @ColumnInfo(name = "dueDate") val dueDate: String = "",
    @ColumnInfo(name = "disciplineName") val disciplineName: String = ""
)