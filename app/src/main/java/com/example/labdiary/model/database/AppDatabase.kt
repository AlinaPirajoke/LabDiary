package com.example.labdiary.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.labdiary.model.database.entities.CompletedLabDbEntity
import com.example.labdiary.model.database.entities.DisciplineDbEntity

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE complited_lab DROP COLUMN number")
        database.execSQL("ALTER TABLE complited_lab ADD _date date")
    }
}

@Database(
    version = 3,
    entities = [
        DisciplineDbEntity::class,
        CompletedLabDbEntity::class
    ],
//    autoMigrations = [
//        AutoMigration(
//            from = 1,
//            to = 2,
//            spec = AppDatabase.AppDatabaseMigration::class
//        )
//    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getLabsDao(): LabsDao
    abstract fun getDisciplineDao(): DisciplineDao

//    @DeleteColumn(tableName = "complited_lab", columnName = "number")
    class AppDatabaseMigration : AutoMigrationSpec {}
}