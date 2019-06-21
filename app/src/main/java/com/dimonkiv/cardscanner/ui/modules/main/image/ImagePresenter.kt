package com.dimonkiv.cardscanner.ui.modules.main.image

class ImagePresenter(private val fragment: ImageFragment): ImageContract.Presenter {
    private lateinit var view: ImageView

    init {

    }

    override fun setView(view: ImageView) {
        this.view = view
    }
}