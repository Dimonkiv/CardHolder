package com.dimonkiv.cardscanner.ui.modules.main.image

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.FragmentData
import com.dimonkiv.cardscanner.ui.modules.main.MainActivity
import com.dimonkiv.cardscanner.utill.FragmentById

class ImageFragment: Fragment(), ImageContract.Fragment {
    private lateinit var root: View
    private lateinit var presenter: ImagePresenter
    private lateinit var view: ImageView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_image, container, false)

        initPresenter()
        initView()

        return root
    }

    private fun initPresenter() {
        presenter = ImagePresenter(this)
    }

    private fun initView() {
        view = ImageView(presenter, context!!, root)
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }

    override fun showPreviousFragment() {
        getMainActivity().changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
    }
}