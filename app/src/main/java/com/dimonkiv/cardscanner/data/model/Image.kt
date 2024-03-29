package com.dimonkiv.cardscanner.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "IMAGE")
data class Image(@PrimaryKey(autoGenerate = true) var id: Int,
                 var imageId: Int,
                 var isUsed: Boolean)