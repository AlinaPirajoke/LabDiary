package com.example.labdiary.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.labdiary.model.database.entities.DisciplineDbEntity

@Dao
interface DisciplineDao {

    @Insert(entity = DisciplineDbEntity::class)
    fun insertNewDiscipline(discipline: DisciplineDbEntity)

    @Query("SELECT * FROM discipline where semester_number = :semester;")
    fun getDisciplinesFromSemester(semester: Int): List<DisciplineDbEntity>

    @Query("SELECT COUNT(*) FROM discipline where semester_number = :semester;")
    fun getDisciplinesQuantityFromSemester(semester: Int): Int

    @Query("SELECT SUM(labs_quantity) FROM discipline where semester_number = :semester;")
    fun getTotalLabsQuantityFromSemester(semester: Int): Int

    @Query("SELECT * FROM discipline as d where d.semester_number = :semester and d.labs_quantity > (select count(*) from completed_lab as l where l.discipline_id = d.id);")
    abstract fun getActualDisciplinesFromSemester(semester: Int): List<DisciplineDbEntity>
}