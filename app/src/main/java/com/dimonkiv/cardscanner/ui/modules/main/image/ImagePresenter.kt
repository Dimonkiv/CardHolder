package com.dimonkiv.cardscanner.ui.modules.main.image

import com.dimonkiv.cardscanner.data.db.AppDatabase
import com.dimonkiv.cardscanner.data.db.InitDatabase
import com.dimonkiv.cardscanner.data.model.Image

class ImagePresenter(private val fragment: ImageFragment): ImageContract.Presenter {
    private lateinit var view: ImageView
    private lateinit var db: AppDatabase

    private val imageList = ArrayList<Image>()

    init {
        initDatabase()
        loadData()
    }

    private fun initDatabase() {
        db = InitDatabase.getInstance(fragment.context!!).getDatabase()
    }

    override fun setView(view: ImageView) {
        this.view = view
        showInitialData()
    }

    private fun loadData() {
        imageList.addAll(db.imageDao().getUnusedImages())
    }

    private fun showInitialData() {
        view.showImages(imageList)
    }

}