package com.dimonkiv.cardscanner.ui.modules.main.addcategory

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import com.dimonkiv.cardscanner.R

class AddCategoryView(private val presenter: AddCategoryPresenter,
                      private val context: Context,
                      private val view: View) : AddCategoryContract.View {
    private lateinit var imageContainerRL: RelativeLayout
    private lateinit var iconIV: ImageView
    private lateinit var categoryET: EditText
    private lateinit var cancelBtn: Button
    private lateinit var addBtn: Button

    init {
        initUI()
        setListeners()
        presenter.setView(this)
    }

    private fun initUI() {
        imageContainerRL = view.findViewById(R.id.add_image_rl)
        iconIV = view.findViewById(R.id.icon_iv)
        categoryET = view.findViewById(R.id.category_et)
        cancelBtn = view.findViewById(R.id.cancel_btn)
        addBtn = view.findViewById(R.id.add_btn)
    }

    private fun setListeners() {
        imageContainerRL.setOnClickListener {  }

        cancelBtn.setOnClickListener {  }

        addBtn.setOnClickListener {  }
    }
}