package com.example.labdiary.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "discipline",
    indices = [Index("id")])
data class DisciplineDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "semester_number") val semester: Int,
    @ColumnInfo(name = "labs_quantity") val labs: Int
)