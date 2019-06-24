package com.dimonkiv.cardscanner.ui.modules.main.carddetail

import android.content.Context
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.ui.modules.main.MainActivity
import com.dimonkiv.cardscanner.ui.widgets.CircleIcon

class CardDetailView(private val presenter: CardDetailPresenter,
                     private val activity: MainActivity,
                     private val context: Context,
                     private val view: View) : CardDetailContract.View {

    private lateinit var toolbar: Toolbar

    private lateinit var titleTV: TextView
    private lateinit var categoryTV: TextView
    private lateinit var imageCI: CircleIcon

    private lateinit var phoneIV: ImageView
    private lateinit var mailIV: ImageView
    private lateinit var shareIV: ImageView
    private lateinit var favouriteIV: ImageView

    private lateinit var addressTV: TextView
    private lateinit var phoneTV: TextView
    private lateinit var emailTV: TextView
    private lateinit var websiteTV: TextView

    init {
        initUI()
        initToolbar()
        setListeners()
        presenter.setView(this)
    }

    private fun initUI() {
        toolbar = view.findViewById(R.id.toolbar)

        titleTV = view.findViewById(R.id.title_tv)
        categoryTV = view.findViewById(R.id.category_tv)
        imageCI = view.findViewById(R.id.icon_ci)

        phoneIV = view.findViewById(R.id.phone_iv)
        mailIV = view.findViewById(R.id.email_iv)
        shareIV = view.findViewById(R.id.share_iv)
        favouriteIV = view.findViewById(R.id.favourite_iv)

        addressTV = view.findViewById(R.id.address_tv)
        phoneTV = view.findViewById(R.id.phone_tv)
        emailTV = view.findViewById(R.id.email_tv)
        websiteTV = view.findViewById(R.id.website_tv)
    }

    private fun initToolbar() {
        activity.setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {  }
    }

    private fun setListeners() {
        phoneIV.setOnClickListener {  }

        mailIV.setOnClickListener {  }

        shareIV.setOnClickListener {  }

        favouriteIV.setOnClickListener {  }
    }

    override fun showTitle(title: String) {
        titleTV.text = title
        activity.supportActionBar?.title = title
    }

    override fun showCategory(category: String) {
        categoryTV.text = category
    }

    override fun showImage(image: Int) {
        imageCI.setIconDrawable(image)
    }

    override fun showEmail(email: String) {
        emailTV.text = email
    }

    override fun showPhone(phone: String) {
        phoneTV.text = phone
    }

    override fun showWebsite(website: String) {
        websiteTV.text = website
    }

    override fun showAddress(address: String) {
        addressTV.text = address
    }
}