package com.example.labdiary.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "completed_lab",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = DisciplineDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["discipline_id"]
        )
    ]
)
data class CompletedLabDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "discipline_id") val discipline: Int,
    @ColumnInfo(name = "number") val number: Int,
    @ColumnInfo(name = "_date") val date: String,
)