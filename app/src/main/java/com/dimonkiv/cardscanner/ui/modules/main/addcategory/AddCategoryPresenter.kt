package com.dimonkiv.cardscanner.ui.modules.main.addcategory

class AddCategoryPresenter(private val fragment: AddCategoryFragment): AddCategoryContract.Presenter {

    private lateinit var view: AddCategoryView

    init {

    }

    override fun setView(view: AddCategoryView) {
        this.view = view
    }

    override fun onCancelButtonClick() {
        fragment.showPreviousFragment()
    }

    override fun onAddButtonClick() {

    }

    override fun onSelectImageButtonClick() {
        fragment.showImageFragment()
    }
}