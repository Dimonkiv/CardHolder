package com.dimonkiv.cardscanner.ui.modules.main.dashboard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.FragmentData
import com.dimonkiv.cardscanner.ui.modules.main.MainActivity
import com.dimonkiv.cardscanner.ui.modules.main.carddetail.CardDetailFragment
import com.dimonkiv.cardscanner.utill.FragmentById

class DashboardFragment: Fragment(), DashboardContract.Fragment {

    private lateinit var root: View
    private lateinit var presenter: DashboardPresenter
    private lateinit var view: DashboardView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        initPresenter()
        initView()

        return root
    }

    private fun initPresenter() {
        presenter = DashboardPresenter(this)
    }

    private fun initView() {
        view = DashboardView(this, presenter, root, activity as MainActivity, context!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        view.onCreateOptionsMenu(menu, inflater)
    }

    override fun showCardDetailFragment(cardId: Int) {
        (activity as MainActivity).changeFragment(FragmentData(FragmentById.CARD_DETAIL_FRAGMENT,
                CardDetailFragment.getBundle(cardId)))
    }
}