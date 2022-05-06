package com.example.test553.database

import android.content.Context
import androidx.room.*

@Database(version = 2,entities = [NoteDbModel::class, ColorDbModel::class,ContactDbModel::class,])
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun colorDao(): ColorDao
    abstract fun contactDao(): ContactDao
    companion object {
        private const val DATABASE_NAME = "note-maker-database"
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            var instance = INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance
            }

            return instance
        }
    }
}