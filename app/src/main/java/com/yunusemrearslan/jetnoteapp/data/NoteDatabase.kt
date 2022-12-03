package com.yunusemrearslan.jetnoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.yunusemrearslan.jetnoteapp.model.Note
import com.yunusemrearslan.jetnoteapp.util.DateConverter
import com.yunusemrearslan.jetnoteapp.util.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class,UUIDConverter::class)
abstract class NoteDatabase:RoomDatabase(){
    abstract fun noteDao():NoteDatabaseDao
}