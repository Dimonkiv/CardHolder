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

        var image = Image(1, R.drawable.ic_ct_work, false)
        imageDao.insert(image)

        image = Image(2, R.drawable.ic_ct_shop, false)
        imageDao.insert(image)

        image = Image(3, R.drawable.ic_ct_car, false)
        imageDao.insert(image)

        image = Image(4, R.drawable.ic_ct_health, false)
        imageDao.insert(image)

        image = Image(5, R.drawable.ic_ct_ball, false)
        imageDao.insert(image)

        image = Image(6, R.drawable.ic_ct_bank, false)
        imageDao.insert(image)

        image = Image(7, R.drawable.ic_ct_credit, false)
        imageDao.insert(image)

        image = Image(8, R.drawable.ic_ct_person, false)
        imageDao.insert(image)

        image = Image(9, R.drawable.ic_ct_plane, false)
        imageDao.insert(image)

        image = Image(10, R.drawable.ic_ct_potato, false)
        imageDao.insert(image)

        image = Image(11, R.drawable.ic_ct_restaurant, false)
        imageDao.insert(image)

        image = Image(12, R.drawable.ic_ct_scooter, false)
        imageDao.insert(image)

        image = Image(13, R.drawable.ic_ct_shampoo, false)
        imageDao.insert(image)

        image = Image(14, R.drawable.ic_ct_support, false)
        imageDao.insert(image)

        image = Image(15, R.drawable.ic_ct_box, false)
        imageDao.insert(image)

        image = Image(16, R.drawable.ic_ct_wine, false)
        imageDao.insert(image)
    }

    private fun initCategoryDB() {
        val categoryDao = InitDatabase.getInstance(this).getDatabase()
        val imageDAO = InitDatabase.getInstance(this).getDatabase().imageDao()

        var category = Category(1,"Робота",1)
        categoryDao.categoryDao().insert(category)
        imageDAO.setUsed(true, 1)

        category = Category(2,"Магазини",2)
        categoryDao.categoryDao().insert(category)
        imageDAO.setUsed(true, 2)

        category = Category(3,"Авто",3)
        categoryDao.categoryDao().insert(category)
        imageDAO.setUsed(true, 3)

        category = Category(4,"Здоров’я",4)
        categoryDao.categoryDao().insert(category)
        imageDAO.setUsed(true, 4)
    }
}