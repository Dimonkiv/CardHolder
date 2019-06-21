package com.dimonkiv.cardscanner.ui.modules.main.image

import com.dimonkiv.cardscanner.data.TempLocalStorage
import com.dimonkiv.cardscanner.data.db.AppDatabase
import com.dimonkiv.cardscanner.data.db.InitDatabase
import com.dimonkiv.cardscanner.data.model.Image

class ImagePresenter(private val fragment: ImageFragment): ImageContract.Presenter {

    private lateinit var view: ImageView
    private lateinit var db: AppDatabase

    private val imageList = ArrayList<Image>()

    private var selectedImageId = 0

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

    override fun onCancelButtonClick() {
        fragment.showPreviousFragment()
    }

    override fun onSelectButtonClick() {
        TempLocalStorage.getInstance().setSelectedImageId(selectedImageId)
        fragment.showPreviousFragment()
    }

    override fun onSelectItem(imageId: Int) {
        selectedImageId = imageId
    }

}