package com.example.noter.di

import android.content.Context
import androidx.room.Room
import com.example.noter.data.NoteDataBase
import com.example.noter.data.NoteDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideNotesDao(noteDataBase: NoteDataBase): NoteDataBaseDao = noteDataBase.noteDao()
    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context : Context) : NoteDataBase
            = Room.databaseBuilder(
        context,
        NoteDataBase::class.java,
        "notes_db")
        .fallbackToDestructiveMigration()
        .build()
}