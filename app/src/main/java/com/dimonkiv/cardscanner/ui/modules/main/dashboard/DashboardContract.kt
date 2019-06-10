package com.dimonkiv.cardscanner.ui.modules.main.dashboard

import android.view.Menu
import android.view.MenuInflater
import com.dimonkiv.cardscanner.data.model.BusinessCard

interface DashboardContract {

    interface View {
        fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)

        fun showCardList(cardList: List<BusinessCard>)
    }

    interface Presenter {
        fun setView(view: DashboardView)

        fun onCardItemClick()
    }

    interface Fragment
}