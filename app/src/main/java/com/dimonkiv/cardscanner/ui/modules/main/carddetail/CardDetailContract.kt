package com.dimonkiv.cardscanner.ui.modules.main.carddetail

interface CardDetailContract {

    interface View {
        fun showTitle(title: String)

        fun showCategory(category: String)

        fun showImage(image: Int)

        fun showEmail(email: String)

        fun showPhone(phone: String)

        fun showWebsite(website: String)

        fun showAddress(address: String)

        fun setFavouriteMode()

        fun setNotFavouriteMode()
    }

    interface Presenter {
        fun setView(view: CardDetailView)

        fun setCardId(cardId: Int)

        fun onFavouriteButtonClick()
    }

    interface Fragment
}