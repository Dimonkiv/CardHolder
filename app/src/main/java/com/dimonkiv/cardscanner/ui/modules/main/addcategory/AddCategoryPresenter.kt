package com.dimonkiv.cardscanner.ui.modules.main.addcategory

import com.dimonkiv.cardscanner.data.TempLocalStorage
import com.dimonkiv.cardscanner.data.db.AppDatabase
import com.dimonkiv.cardscanner.data.db.InitDatabase
import com.dimonkiv.cardscanner.data.model.Category

class AddCategoryPresenter(private val fragment: AddCategoryFragment): AddCategoryContract.Presenter {

    private lateinit var view: AddCategoryView
    private lateinit var db: AppDatabase

    private val category = Category()

    init {
        initDatabase()
    }

    private fun initDatabase() {
        db = InitDatabase.getInstance(fragment.context!!).getDatabase()
    }

    override fun setView(view: AddCategoryView) {
        this.view = view
    }

    override fun onCancelButtonClick() {
        TempLocalStorage.getInstance().setSelectedImageId(null)
        fragment.showPreviousFragment()
    }

    override fun onAddButtonClick() {
        TempLocalStorage.getInstance().setSelectedImageId(null)

        if (category.imageId == 0) {
            view.showMessage("Ви не вибрали картинку для категорії!")
            return
        }

        if (category.title.isEmpty()) {
            view.showMessage("Введіть назву категорії!")
            return
        }

        addCategory()
        view.showMessage("Нову категорію додано!")
        fragment.showPreviousFragment()
    }

    private fun addCategory() {
        db.categoryDao().insert(category)
    }

    override fun onSelectImageButtonClick() {
        fragment.showImageFragment()
    }

    override fun onCategoryTextChanged(category: String) {
        this.category.title = category
    }

    override fun checkingImageSelected() {
        val selectedImageId = TempLocalStorage.getInstance().getSelectedImageId()

        if (selectedImageId != null) {
            category.imageId = selectedImageId
            loadImage(selectedImageId)
        }
    }

    private fun loadImage(selectedImageId: Int) {
        val image = db.imageDao().getImageById(selectedImageId)
        view.showImage(image)
    }
}