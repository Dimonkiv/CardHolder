package com.dimonkiv.cardscanner.ui.modules.main.carddetail

import com.dimonkiv.cardscanner.data.db.AppDatabase
import com.dimonkiv.cardscanner.data.db.InitDatabase
import com.dimonkiv.cardscanner.data.model.BusinessCard
import com.dimonkiv.cardscanner.data.model.Category
import com.dimonkiv.cardscanner.data.model.Image

class CardDetailPresenter(private val fragment: CardDetailFragment) : CardDetailContract.Presenter {
    private lateinit var view: CardDetailView
    private lateinit var db: AppDatabase

    private var card: BusinessCard? = null
    private var category: Category? = null
    private var image: Image? = null

    private var cardId = 0

    init {
        initDatabase()
    }

    private fun initDatabase() {
        db = InitDatabase.getInstance(fragment.context!!).getDatabase()
    }

    override fun setView(view: CardDetailView) {
        this.view = view
        showInitialData()
    }


    override fun setCardId(cardId: Int) {
        this.cardId = cardId
        loadData()
    }

    private fun loadData() {
        card = db.businessCardDao().getById(cardId)
        category = db.categoryDao().getById(card?.categoryId!!)
        image = db.imageDao().getImageById(category?.imageId!!)
    }

    private fun showInitialData() {
        showTitle()
        showImage()
        showCategory()
        showPhone()
        showEmail()
        showWebsite()
        showAddress()
    }

    private fun showTitle() {
        val title = card?.name
        if (title != null) {
            view.showTitle(title)
        }
    }

    private fun showImage() {
        view.showImage(image?.imageId!!)
    }

    private fun showCategory() {
        val categoryStr = category?.title
        if (categoryStr != null) {
            view.showCategory(categoryStr)
        }
    }

    private fun showPhone() {
        val phone = card?.phone
        if (phone != null) {
            view.showPhone(phone)
        }
    }

    private fun showEmail() {
        val email = card?.email
        if (email != null) {
            view.showEmail(email)
        }
    }

    private fun showWebsite() {
        val website = card?.site
        if (website != null) {
            view.showWebsite(website)
        }
    }

    private fun showAddress() {
        val address = card?.location
        if (address != null) {
            view.showAddress(address)
        }
    }

}