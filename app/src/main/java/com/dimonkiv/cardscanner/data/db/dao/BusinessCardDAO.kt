package com.dimonkiv.cardscanner.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.dimonkiv.cardscanner.data.model.BusinessCard

@Dao
interface BusinessCardDAO {
    @Query("SELECT * FROM BUSINESS_CARD")
    fun getAll(): List<BusinessCard>

    @Query("SELECT * FROM BUSINESS_CARD WHERE categoryId = :categoryId")
    fun getAllByCategoryId(categoryId: Int): List<BusinessCard>

    @Query("SELECT * FROM BUSINESS_CARD WHERE isFavourite = 1")
    fun getFavoriteCards(): List<BusinessCard>

    @Query("UPDATE BUSINESS_CARD SET isFavourite = :isFavourite WHERE id = :cardId")
    fun updateFavourite(isFavourite: Boolean, cardId: Int)

    @Query("SELECT * FROM BUSINESS_CARD WHERE id = :id")
    fun getById(id: Int): BusinessCard

    @Insert
    fun insert(businessCard: BusinessCard)
}