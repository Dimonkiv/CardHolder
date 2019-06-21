package com.dimonkiv.cardscanner.ui.modules.main.addcategory

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.Image

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
        categoryET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        imageContainerRL.setOnClickListener {
            presenter.onSelectImageButtonClick()
        }

        cancelBtn.setOnClickListener {
            presenter.onCancelButtonClick()
        }

        addBtn.setOnClickListener {
            presenter.onAddButtonClick()
        }
    }

    override fun showImage(image: Image) {
        imageContainerRL.background = context.resources.getDrawable(R.drawable.background_circle)
        iconIV.setImageResource(image.imageId)
    }
}