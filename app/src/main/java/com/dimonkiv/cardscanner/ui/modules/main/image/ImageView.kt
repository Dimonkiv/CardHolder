package com.dimonkiv.cardscanner.ui.modules.main.image

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import com.dimonkiv.cardscanner.R

class ImageView(private val presenter: ImagePresenter,
                private val context: Context,
                private val view: View) : ImageContract.View {

    private lateinit var cancelBtn: Button
    private lateinit var selectBtn: Button
    private lateinit var recyclerView: RecyclerView

    init {
        initUI()
        setListeners()
        presenter.setView(this)
    }

    private fun initUI() {
        cancelBtn = view.findViewById(R.id.cancel_btn)
        selectBtn = view.findViewById(R.id.select_btn)
        recyclerView = view.findViewById(R.id.recycler_view)
    }

    private fun setListeners() {
        cancelBtn.setOnClickListener {  }

        selectBtn.setOnClickListener {  }
    }
}