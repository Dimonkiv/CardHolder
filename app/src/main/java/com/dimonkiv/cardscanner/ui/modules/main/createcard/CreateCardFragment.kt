package com.dimonkiv.cardscanner.ui.modules.main.createcard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.ui.modules.main.MainActivity

class CreateCardFragment: Fragment(), CreateCardContract.Fragment {
    private lateinit var root: View
    private lateinit var presenter: CreateCardPresenter
    private lateinit var view: CreateCardView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_create_card, container, false)

        initPresenter()
        initView()

        return root
    }

    private fun initPresenter() {
        presenter = CreateCardPresenter(this)
    }

    private fun initView() {
        view = CreateCardView(this, presenter, activity as MainActivity, root, context!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        view.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        view.onOptionsItemSelected(item)
        return true
    }


}