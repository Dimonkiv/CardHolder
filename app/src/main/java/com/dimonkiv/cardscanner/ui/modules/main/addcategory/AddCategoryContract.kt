package com.dimonkiv.cardscanner.ui.modules.main.addcategory

interface AddCategoryContract {

    interface View

    interface Presenter {
        fun setView(view: AddCategoryView)
    }

    interface Fragment
}