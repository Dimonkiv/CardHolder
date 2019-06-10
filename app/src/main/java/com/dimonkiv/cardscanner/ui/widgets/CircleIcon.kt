package com.dimonkiv.cardscanner.ui.widgets

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout
import com.dimonkiv.cardscanner.R

class CircleIcon(context: Context, attributeSet: AttributeSet) : RelativeLayout(context, attributeSet) {
    private lateinit var iconIV: ImageView

    override fun onFinishInflate() {
        super.onFinishInflate()
        (context as Activity).layoutInflater.inflate(R.layout.view_circle_icon, this)
        initUI()
    }

    private fun initUI() {
        iconIV = findViewById(R.id.icon_iv)
    }

    fun setIconDrawable(imageDrawable: Int) {
        iconIV.setImageDrawable(context.resources.getDrawable(imageDrawable))
    }
}