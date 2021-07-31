package com.atdev.githubproject.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    RepositoryDownloadedEntity::class], version = 5, exportSchema = false)
abstract class RepositoryDatabase : RoomDatabase() {

    abstract fun repositoryDao(): RepositoryDao

    companion object {
        @Volatile
        private var INSTANCE: RepositoryDatabase? = null

        fun getDatabase(context: Context): RepositoryDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RepositoryDatabase::class.java,
                    "repository_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}