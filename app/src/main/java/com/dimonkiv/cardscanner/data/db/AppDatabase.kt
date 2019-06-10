package com.dimonkiv.cardscanner.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dimonkiv.cardscanner.data.db.dao.BusinessCardDAO
import com.dimonkiv.cardscanner.data.db.dao.CategoryDAO
import com.dimonkiv.cardscanner.data.db.dao.ImageDAO
import com.dimonkiv.cardscanner.data.model.BusinessCard
import com.dimonkiv.cardscanner.data.model.Category
import com.dimonkiv.cardscanner.data.model.Image

@Database(entities = [(BusinessCard::class), (Category::class), (Image::class)], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun businessCardDao(): BusinessCardDAO

    abstract fun categoryDao(): CategoryDAO

    abstract fun imageDao(): ImageDAO

}