package com.dimonkiv.cardscanner.ui.modules.main.image

import com.dimonkiv.cardscanner.data.model.Image


interface ImageContract {

    interface View {
        fun showImages(imageList: List<Image>)
    }

    interface Presenter {
        fun setView(view: ImageView)
    }

    interface Fragment
}