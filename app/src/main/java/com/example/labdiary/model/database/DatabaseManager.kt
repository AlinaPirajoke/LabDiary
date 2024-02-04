package com.example.labdiary.model.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.labdiary.model.database.entities.CompletedLabDbEntity
import com.example.labdiary.model.database.entities.CompletedLabShortInfo
import com.example.labdiary.model.database.entities.DisciplineDbEntity
import java.time.LocalDate

class DatabaseManager(context: Context) {
    private final val TAG = "DbManager"
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database"
    ).fallbackToDestructiveMigration().build()

    private val labsDao = db.getLabsDao()
    private val disDao = db.getDisciplineDao()

    suspend fun testing() {
        Log.d(TAG, "Test is starting...")

//        disDao.insertNewDiscipline(DisciplineDbEntity(0, "Жизнь", 6, 2))
//        disDao.insertNewDiscipline(DisciplineDbEntity(0, "Аниме", 6, 5))
//        disDao.insertNewDiscipline(DisciplineDbEntity(0, "Скейтборд", 6, 10))
//        labsDao.insertNewLab(CompletedLabDbEntity(0, 3, "2010-01-03"))

        Log.d(TAG, "Test result: " + disDao.getTotalLabsQuantityFromSemester(6).toString())
        Log.d(TAG, "Test result: " + labsDao.getCompletedLabsQuantityFromSemester(6).toString())
    }

    suspend fun getTotalLabsQuantityFromSemester(semester: Int): Int =
        disDao.getTotalLabsQuantityFromSemester(semester)

    suspend fun getCompletedLabsQuantityFromSemester(semester: Int): Int =
        labsDao.getCompletedLabsQuantityFromSemester(semester)

    suspend fun getDisciplinesFromSemester(semester: Int): List<DisciplineDbEntity> =
        disDao.getDisciplinesFromSemester(semester)

    suspend fun getCompletedLabsFromDiscipline(discipline: Int): Int =
        labsDao.getCompletedLabsQuantityFromDiscipline(discipline)

    suspend fun insertNewLab(lab: CompletedLabDbEntity) = labsDao.insertNewLab(lab)

    suspend fun insertNewLab(discipline: Int, number: Int) = labsDao.insertNewLab(
        CompletedLabDbEntity(
            id = 0,
            discipline = discipline,
            date = LocalDate.now().toString(),
            number = number
        )
    )

    suspend fun insertNewDis(discipline: DisciplineDbEntity) =
        disDao.insertNewDiscipline(discipline)

    suspend fun deleteLab(lab: CompletedLabDbEntity) = labsDao.deleteLab(lab)

    suspend fun getActualDisciplinesFromSemester(semester: Int): List<DisciplineDbEntity> = disDao.getActualDisciplinesFromSemester(semester)

    suspend fun getAllCompletedLabsFromDiscipline(discipline: Int): List<Int> = labsDao.getAllCompletedLabsFromDiscipline(discipline = discipline)

    suspend fun getAllCompletedLabs(): List<CompletedLabShortInfo> = labsDao.getAllCompletedLabs()

    suspend fun getDisciplinesQuantityFromSemester(semester: Int): Int = disDao.getDisciplinesQuantityFromSemester(semester)
}