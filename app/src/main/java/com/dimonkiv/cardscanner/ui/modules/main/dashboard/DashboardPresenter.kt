package com.dimonkiv.cardscanner.ui.modules.main.dashboard

import com.dimonkiv.cardscanner.data.db.AppDatabase
import com.dimonkiv.cardscanner.data.db.InitDatabase
import com.dimonkiv.cardscanner.data.model.BusinessCard

class DashboardPresenter(private val fragment: DashboardFragment): DashboardContract.Presenter {
    private lateinit var view: DashboardView
    private val cardList = ArrayList<BusinessCard>()
    private var db: AppDatabase = InitDatabase.getInstance(fragment.context!!).getDatabase()


    init {
        cardList.addAll(db.businessCardDao().getAll())
    }

    override fun setView(view: DashboardView) {
        this.view = view
        view.showCardList(cardList)
    }

    override fun onCardItemClick() {

    }
}