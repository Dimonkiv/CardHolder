package com.dimonkiv.cardscanner.ui.modules.main.dashboard

import com.dimonkiv.cardscanner.data.db.AppDatabase
import com.dimonkiv.cardscanner.data.db.InitDatabase
import com.dimonkiv.cardscanner.data.model.BusinessCard
import com.dimonkiv.cardscanner.data.model.Image

class DashboardPresenter(private val fragment: DashboardFragment): DashboardContract.Presenter {
    private lateinit var view: DashboardView
    private val cardList = ArrayList<BusinessCard>()
    private val imageList = ArrayList<Image>()
    private var db: AppDatabase = InitDatabase.getInstance(fragment.context!!).getDatabase()


    init {
        cardList.addAll(db.businessCardDao().getAll())
        loadImageList()
    }

    private fun loadImageList() {
        for (it in cardList) {
            val category = db.categoryDao().getById(it.categoryId)
            val image = db.imageDao().getImageById(category.imageId)
            imageList.add(image)
        }
    }

    override fun setView(view: DashboardView) {
        this.view = view
        view.showCardList(cardList, imageList)
    }

    override fun onCardItemClick(cardId: Int) {
        fragment.showCardDetailFragment(cardId)
    }
}