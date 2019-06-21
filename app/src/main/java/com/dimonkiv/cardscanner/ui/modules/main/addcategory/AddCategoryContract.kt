package com.dimonkiv.cardscanner.ui.modules.main.addcategory

interface AddCategoryContract {

    interface View

    interface Presenter {
        fun setView(view: AddCategoryView)

        fun onCancelButtonClick()

        fun onAddButtonClick()

        fun onSelectImageButtonClick()
    }

    interface Fragment {

        fun showPreviousFragment()

        fun showImageFragment()
    }
}