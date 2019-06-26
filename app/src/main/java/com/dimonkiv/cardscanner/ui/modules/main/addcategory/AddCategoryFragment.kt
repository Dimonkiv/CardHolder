package com.dimonkiv.cardscanner.ui.modules.main.addcategory

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.FragmentData
import com.dimonkiv.cardscanner.ui.modules.main.MainActivity
import com.dimonkiv.cardscanner.ui.modules.main.category.CategoryFragment
import com.dimonkiv.cardscanner.utill.FragmentById

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

    override fun onResume() {
        super.onResume()
        presenter.checkingImageSelected()
    }

    private fun initPresenter() {
        presenter = AddCategoryPresenter(this)
    }

    private fun initView() {
        view = AddCategoryView(presenter, context!!, root)
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }

    override fun showPreviousFragment() {
        getMainActivity().changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
    }

    override fun showImageFragment() {
        getMainActivity().changeFragment(FragmentData(FragmentById.IMAGE_FRAGMENT))
    }

    override fun sendChangeCategoriesBroadcast() {
        Intent().also { intent ->
            intent.action = CategoryFragment.NEW_CATEGORY_FILTER
            getMainActivity().sendBroadcast(intent)
        }
    }
}