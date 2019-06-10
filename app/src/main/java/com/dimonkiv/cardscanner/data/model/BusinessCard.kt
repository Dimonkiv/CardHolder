package com.dimonkiv.cardscanner.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "BUSINESS_CARD")
data class BusinessCard(@PrimaryKey(autoGenerate = true) var id: Int = 0,
                        var name: String = "",
                        var description: String = "",
                        var site: String = "",
                        var email: String = "",
                        var phone: String = "",
                        var location: String = "",
                        var categoryId: Int = 0)