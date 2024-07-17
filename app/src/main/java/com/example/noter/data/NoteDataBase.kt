package com.example.noter.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noter.model.Note
import com.example.noter.util.DateConverter
import com.example.noter.util.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun noteDao() : NoteDataBaseDao
}