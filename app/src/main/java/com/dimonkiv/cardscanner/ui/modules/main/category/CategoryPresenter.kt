package com.dimonkiv.cardscanner.ui.modules.main.category

import com.dimonkiv.cardscanner.data.db.AppDatabase
import com.dimonkiv.cardscanner.data.db.InitDatabase
import com.dimonkiv.cardscanner.data.model.Category
import com.dimonkiv.cardscanner.data.model.Image

class CategoryPresenter(private val fragment: CategoryFragment): CategoryContract.Presenter {
    private lateinit var view: CategoryView
    private lateinit var db: AppDatabase

    private val categoryList = ArrayList<Category>()
    private val imageList = ArrayList<Image>()


    /*----------------------------------Initialization--------------------------------------------*/
    init {
        initDB()
    }

    private fun initDB() {
        db = InitDatabase.getInstance(fragment.context!!).getDatabase()
    }

    override fun setView(view: CategoryView) {
        this.view = view
    }

    override fun loadData() {
        loadCategoryData()
        loadImageData()
        showInitialData()
    }

    private fun loadCategoryData() {
        val categoryDAO = db.categoryDao()
        categoryList.addAll(categoryDAO.getAll())
    }

    private fun loadImageData() {
        val imageDao = db.imageDao()

        for(it in categoryList) {
            val image = imageDao.getImageById(it.imageId)
            imageList.add(image)
        }
    }


    /*-----------------------------------------Listeners------------------------------------------*/
    override fun onAddCategoryClick() {
        fragment.showAddCategoryFragment()
    }

    override fun onSearchButtonClick() {

    }

    override fun onSettingsButtonClick() {

    }

    private fun showInitialData() {
        view.showCategories(categoryList, imageList)
    }
}