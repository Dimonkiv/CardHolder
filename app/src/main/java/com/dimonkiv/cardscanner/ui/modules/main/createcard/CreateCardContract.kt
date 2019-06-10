package com.dimonkiv.cardscanner.ui.modules.main.createcard

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.dimonkiv.cardscanner.data.model.Category

interface CreateCardContract {

    interface View {
        fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)

        fun onOptionsItemSelected(item: MenuItem?): Boolean

        fun showCategories(categoryList: List<Category>)
    }

    interface Presenter {
        fun setView(view: CreateCardView)

        fun onNameTextChanged(name: String)

        fun onPhoneTextChanged(phone: String)

        fun onAddressTextChanged(address: String)

        fun onEmailTextChanged(email: String)

        fun onSiteTextChanged(site: String)

        fun onCategoryItemSelected(categoryId: Int)

        fun onAddButtonClick()
    }

    interface Fragment
}