package com.dimonkiv.cardscanner.data.db

import android.arch.persistence.room.Room
import android.content.Context

class InitDatabase {
    companion object {
        private var instance: InitDatabase? = null
        private var db: AppDatabase? = null

        fun getInstance(context: Context): InitDatabase {
            if(instance == null) {
                instance = InitDatabase()
                initDB(context)
            }

            return instance!!
        }

        private fun initDB(context: Context) {
            db = Room.databaseBuilder(context, AppDatabase::class.java, "card_db")
                .allowMainThreadQueries()
                .build()
        }
    }

    fun getDatabase(): AppDatabase {
        return db!!
    }
}