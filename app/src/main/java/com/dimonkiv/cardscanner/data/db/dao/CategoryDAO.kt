package com.dimonkiv.cardscanner.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.dimonkiv.cardscanner.data.model.Category

@Dao
interface CategoryDAO {
    @Query("SELECT * FROM CATEGORY")
    fun getAll(): List<Category>

    @Query("SELECT * FROM CATEGORY WHERE id = :categoryId")
    fun getById(categoryId: Int): Category

    @Insert
    fun insert(category: Category)
}