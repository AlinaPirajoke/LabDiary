package com.example.labdiary.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        DisciplineDbEntity::class,
        CompletedLabDbEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getLabsDao(): LabsDao
    abstract fun getDisciplineDao(): DisciplineDao
}