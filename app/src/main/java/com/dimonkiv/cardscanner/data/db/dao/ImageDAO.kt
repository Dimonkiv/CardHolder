package com.dimonkiv.cardscanner.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.dimonkiv.cardscanner.data.model.Image

@Dao
interface ImageDAO {

    @Query("SELECT * FROM IMAGE WHERE isUsed = 0")
    fun getUnusedImages(): List<Image>

    @Query("SELECT * FROM IMAGE WHERE id = :id")
    fun getImageById(id: Int): Image

    @Insert
    fun insert(image: Image)

    @Query("UPDATE IMAGE Set isUsed = 1 WHERE id =:id")
    fun setUsed(id: Int)
}