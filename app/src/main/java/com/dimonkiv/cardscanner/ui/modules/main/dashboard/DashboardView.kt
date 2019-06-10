package com.dimonkiv.cardscanner.ui.modules.main.dashboard

import android.content.Context
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.BusinessCard
import com.dimonkiv.cardscanner.ui.adapter.CardAdapter
import com.dimonkiv.cardscanner.ui.modules.main.MainActivity
import com.dimonkiv.cardscanner.utill.callback.CardCallback

class DashboardView(
        private val fragment: DashboardFragment,
        private val presenter: DashboardPresenter,
        private val view: View,
        private val activity: MainActivity,
        private val context: Context) : DashboardContract.View, CardCallback {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardAdapter

    init {
        initUI()
        initToolbar()
        initAdapter()
        presenter.setView(this)
    }

    private fun initUI() {
        toolbar = view.findViewById(R.id.toolbar)
        recyclerView = view.findViewById(R.id.recycler_view)
    }

    private fun initToolbar() {
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.title = "Головна"
        fragment.setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_toolbar, menu)
    }

    private fun initAdapter() {
        adapter = CardAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    override fun onCardItemClick() {
        presenter.onCardItemClick()
    }

    override fun showCardList(cardList: List<BusinessCard>) {
        adapter.setCardList(cardList)
    }
}