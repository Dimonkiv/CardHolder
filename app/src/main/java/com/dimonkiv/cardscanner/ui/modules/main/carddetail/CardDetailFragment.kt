package com.dimonkiv.cardscanner.ui.modules.main.carddetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.ui.modules.main.MainActivity

class CardDetailFragment : Fragment(), CardDetailContract.Fragment {

    companion object {
        private const val CARD_ID = "cardId"

        fun getBundle(cardId: Int): Bundle {
            val bundle = Bundle()
            bundle.putInt(CARD_ID, cardId)

            return bundle
        }
    }

    private lateinit var root: View
    private lateinit var presenter: CardDetailPresenter
    private lateinit var view: CardDetailView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_card_detail, container, false)

        initPresenter()
        resumeBundle()
        initView()

        return root
    }

    private fun initPresenter() {
        presenter = CardDetailPresenter(this)
    }

    private fun initView() {
        view = CardDetailView(presenter, getMainActivity(), context!!, root)
    }

    private fun resumeBundle() {
        if (arguments?.containsKey(CARD_ID)!!) {
            presenter.setCardId(arguments?.getInt(CARD_ID)!!)
        }
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}