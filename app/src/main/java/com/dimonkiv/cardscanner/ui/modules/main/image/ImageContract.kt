package com.dimonkiv.cardscanner.ui.modules.main.image

interface ImageContract {

    interface View

    interface Presenter {
        fun setView(view: ImageView)
    }

    interface Fragment
}