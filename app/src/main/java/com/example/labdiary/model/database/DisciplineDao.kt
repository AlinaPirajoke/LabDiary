package com.example.labdiary.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DisciplineDao {

    @Insert(entity = DisciplineDbEntity::class)
    fun insertNewDiscipline(discipline: DisciplineDbEntity)

    @Query("SELECT * FROM discipline where semester_number = :semester;")
    fun getDisciplinesFromSemester(semester: Int): List<DisciplineDbEntity>

    @Query("SELECT SUM(labs_quantity) FROM discipline where semester_number = :semester;")
    fun getTotalLabsQuantityFromSemester(semester: Int): Int
}