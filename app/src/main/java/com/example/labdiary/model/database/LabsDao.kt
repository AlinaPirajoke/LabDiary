package com.example.labdiary.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.labdiary.model.database.entities.CompletedLabDbEntity
import com.example.labdiary.model.database.entities.CompletedLabShortInfo

@Dao
interface LabsDao {

    @Insert(entity = CompletedLabDbEntity::class)
    fun insertNewLab(labs: CompletedLabDbEntity)

    @Delete(entity = CompletedLabDbEntity::class)
    fun deleteLab(lab: CompletedLabDbEntity)

//    @Query("DELETE FROM completed_lab WHERE id = :id")
//    fun deleteLab(id: Int)

    @Query("SELECT COUNT(id) FROM completed_lab WHERE discipline_id = :discipline;")
    fun getCompletedLabsQuantityFromDiscipline(discipline: Int): Int

    @Query("SELECT number FROM completed_lab WHERE discipline_id = :discipline;")
    fun getAllCompletedLabsFromDiscipline(discipline: Int): List<Int>

    @Query("SELECT COUNT(labs.id) FROM completed_lab AS labs JOIN discipline " +
            "ON discipline.id = labs.discipline_id WHERE discipline.semester_number = :semester;")
    fun getCompletedLabsQuantityFromSemester(semester: Int): Int

    @Query("SELECT l.id, l.number, l._date AS dueDate, d.name AS disciplineName FROM completed_lab AS l JOIN discipline AS d ON l.discipline_id = d.id ORDER BY l._date DESC;")
    fun getAllCompletedLabs(): List<CompletedLabShortInfo>
}