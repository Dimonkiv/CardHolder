package com.dimonkiv.cardscanner.ui.modules.main.category

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.dimonkiv.cardscanner.data.model.Category
import com.dimonkiv.cardscanner.data.model.Image

interface CategoryContract {

    interface View {
        fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)

        fun onOptionsItemSelected(item: MenuItem?)

        fun showCategories(categoryList: List<Category>, imageList: List<Image>)
    }

    interface Presenter {
        fun setView(view: CategoryView)

        fun onAddCategoryClick()

        fun onSearchButtonClick()

        fun onSettingsButtonClick()
    }

    interface Fragment
}