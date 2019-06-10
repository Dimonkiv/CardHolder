package com.dimonkiv.cardscanner.ui.modules.main.category

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.ui.modules.main.MainActivity

class CategoryFragment: Fragment(), CategoryContract.Fragment {
    private lateinit var root: View
    private lateinit var view: CategoryView
    private lateinit var presenter: CategoryPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_category, container, false)

        initPresenter()
        initView()

        return root
    }

    private fun initPresenter() {
        presenter = CategoryPresenter(this)
    }

    private fun initView() {
        view = CategoryView(presenter, context!!, getMainActivity(), this, root)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        view.onCreateOptionsMenu(menu, inflater)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        view.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }

}