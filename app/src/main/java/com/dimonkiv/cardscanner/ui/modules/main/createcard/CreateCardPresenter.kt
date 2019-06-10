package com.dimonkiv.cardscanner.ui.modules.main.createcard

import com.dimonkiv.cardscanner.data.db.AppDatabase
import com.dimonkiv.cardscanner.data.db.InitDatabase
import com.dimonkiv.cardscanner.data.model.BusinessCard
import com.dimonkiv.cardscanner.data.model.Category

class CreateCardPresenter(private val fragment: CreateCardFragment): CreateCardContract.Presenter {

    private lateinit var view: CreateCardView
    private var db: AppDatabase = InitDatabase.getInstance(fragment.context!!).getDatabase()

    private var categoryList = ArrayList<Category>()
    private var card = BusinessCard()



    init {
        loadData()
    }

    private fun loadData() {
        categoryList.addAll(db.categoryDao().getAll())
    }

    override fun setView(view: CreateCardView) {
        this.view = view
        view.showCategories(categoryList)
    }

    override fun onAddButtonClick() {
        db.businessCardDao().insert(card)
    }

    override fun onNameTextChanged(name: String) {
        card.name = name
    }

    override fun onPhoneTextChanged(phone: String) {
        card.phone = phone
    }

    override fun onAddressTextChanged(address: String) {
        card.location = address
    }

    override fun onEmailTextChanged(email: String) {
        card.email = email
    }

    override fun onSiteTextChanged(site: String) {
        card.site = site
    }

    override fun onCategoryItemSelected(categoryId: Int) {
        card.categoryId = categoryId
    }
}