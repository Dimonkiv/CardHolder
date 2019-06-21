package com.dimonkiv.cardscanner.ui.modules.main.addcategory

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimonkiv.cardscanner.R

class AddCategoryFragment: Fragment(), AddCategoryContract.Fragment {
    private lateinit var root: View
    private lateinit var presenter: AddCategoryPresenter
    private lateinit var view: AddCategoryView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_add_category, container, false)

        initPresenter()
        initView()

        return root
    }

    private fun initPresenter() {
        presenter = AddCategoryPresenter(this)
    }

    private fun initView() {
        view = AddCategoryView(presenter, context!!, root)
    }
}