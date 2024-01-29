package com.example.labdiary.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LabsDao {

    @Insert(entity = CompletedLabDbEntity::class)
    fun insertNewLab(labs: CompletedLabDbEntity)

    @Query("SELECT COUNT(id) FROM completed_lab WHERE discipline_id = :discipline;")
    fun getCompletedLabsFromDiscipline(discipline: Int): List<Int>

    @Query("SELECT COUNT(labs.id) FROM completed_lab AS labs JOIN discipline " +
            "ON discipline.id = labs.discipline_id WHERE discipline.semester_number = :semester;")
    fun getCompletedLabsQuantityFromSemester(semester: Int): Int
}