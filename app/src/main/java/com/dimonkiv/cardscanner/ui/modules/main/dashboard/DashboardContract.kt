package com.dimonkiv.cardscanner.ui.modules.main.dashboard

import android.view.Menu
import android.view.MenuInflater
import com.dimonkiv.cardscanner.data.model.BusinessCard
import com.dimonkiv.cardscanner.data.model.Image

interface DashboardContract {

    interface View {
        fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)

        fun showCardList(cardList: List<BusinessCard>, imageList: List<Image>)
    }

    interface Presenter {
        fun setView(view: DashboardView)

        fun onCardItemClick(cardId: Int)
    }

    interface Fragment {
        fun showCardDetailFragment(cardId: Int)
    }
}