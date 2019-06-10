package com.dimonkiv.cardscanner.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "CATEGORY")
data class Category(@PrimaryKey(autoGenerate = true) var id: Int,
                    var title: String,
                    var imageId: Int) {

    override fun toString(): String {
        return title
    }
}