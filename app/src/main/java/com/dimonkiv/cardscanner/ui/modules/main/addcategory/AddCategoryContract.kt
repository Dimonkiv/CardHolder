package com.dimonkiv.cardscanner.ui.modules.main.addcategory

import com.dimonkiv.cardscanner.data.model.Image

interface AddCategoryContract {

    interface View {
        fun showImage(image: Image)

        fun showMessage()
    }

    interface Presenter {
        fun setView(view: AddCategoryView)

        fun checkingImageSelected()

        fun onCancelButtonClick()

        fun onAddButtonClick()

        fun onSelectImageButtonClick()

        fun onCategoryTextChanged(category: String)
    }

    interface Fragment {

        fun showPreviousFragment()

        fun showImageFragment()
    }
}