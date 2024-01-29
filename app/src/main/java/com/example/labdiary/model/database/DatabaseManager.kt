package com.example.labdiary.model.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase

class DatabaseManager(context: Context) {
    private final val TAG = "DbManager"
    private val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database"
        ).build()

    private val labsDao = db.getLabsDao()
    private val disDao = db.getDisciplineDao()

    suspend fun testing() {
        Log.d(TAG, "Test is starting...")
//        disDao.insertNewDiscipline(DisciplineDbEntity(0, "Секс", 6, 3))
//        labsDao.insertNewLab(CompletedLabDbEntity(0, 3, 1))
        Log.d(TAG, "Test result: " + disDao.getTotalLabsQuantityFromSemester(6).toString())
        Log.d(TAG, "Test result: " + labsDao.getCompletedLabsQuantityFromSemester(6).toString())
    }

    suspend fun getTotalLabsQuantityFromSemester(semester: Int): Int = disDao.getTotalLabsQuantityFromSemester(semester)

    suspend fun getCompletedLabsQuantityFromSemester(semester: Int): Int = labsDao.getCompletedLabsQuantityFromSemester(semester)
}