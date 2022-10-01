package com.example.App.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Nota::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun notasDao() : NotasDao
}