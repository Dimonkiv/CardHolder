package com.dimonkiv.cardscanner

import android.app.Application
import com.dimonkiv.cardscanner.data.MainPrefManager
import com.dimonkiv.cardscanner.data.db.InitDatabase
import com.dimonkiv.cardscanner.data.model.Category
import com.dimonkiv.cardscanner.data.model.Image

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val prefManeger = MainPrefManager.getInstance(this)

        if(prefManeger.isFirstTimeLaunch()) {
            initImageDB()
            initCategoryDB()
            prefManeger.setFirstTimeLaunch(false)
        }
    }

    private fun initImageDB() {
        val imageDao = InitDatabase.getInstance(this).getDatabase().imageDao()

        var image = Image(1, R.drawable.icon_work)
        imageDao.insert(image)

        image = Image(2, R.drawable.icon_shop)
        imageDao.insert(image)

        image = Image(3, R.drawable.icon_car)
        imageDao.insert(image)

        image = Image(4, R.drawable.icon_health)
        imageDao.insert(image)
    }

    private fun initCategoryDB() {
        val categoryDao = InitDatabase.getInstance(this).getDatabase()
        var category = Category(1,"Робота",1)
        categoryDao.categoryDao().insert(category)

        category = Category(2,"Магазини",2)
        categoryDao.categoryDao().insert(category)

        category = Category(3,"Авто",3)
        categoryDao.categoryDao().insert(category)

        category = Category(4,"Здоров’я",4)
        categoryDao.categoryDao().insert(category)
    }
}