package com.dimonkiv.cardscanner.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.dimonkiv.cardscanner.data.model.Image

@Dao
interface ImageDAO {

    @Query("SELECT * FROM IMAGE WHERE id = :id")
    fun getImageById(id: Int): Image

    @Insert
    fun insert(image: Image)
}