package com.dimonkiv.cardscanner.ui.modules.main.image

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.Image
import com.dimonkiv.cardscanner.ui.adapter.ImageAdapter

class ImageView(private val presenter: ImagePresenter,
                private val context: Context,
                private val view: View) : ImageContract.View {

    private lateinit var cancelBtn: Button
    private lateinit var selectBtn: Button
    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: ImageAdapter

    init {
        initUI()
        initAdapter()
        setListeners()
        presenter.setView(this)
    }

    private fun initUI() {
        cancelBtn = view.findViewById(R.id.cancel_btn)
        selectBtn = view.findViewById(R.id.select_btn)
        recyclerView = view.findViewById(R.id.recycler_view)
    }

    private fun initAdapter() {
        adapter = ImageAdapter()

        recyclerView.layoutManager = GridLayoutManager(context, 4)
        recyclerView.adapter = adapter
    }

    private fun setListeners() {
        cancelBtn.setOnClickListener {  }

        selectBtn.setOnClickListener {  }
    }

    override fun showImages(imageList: List<Image>) {
        adapter.setItems(imageList)
    }
}