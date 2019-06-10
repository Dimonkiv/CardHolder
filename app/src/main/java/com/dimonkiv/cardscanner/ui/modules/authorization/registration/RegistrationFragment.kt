package com.dimonkiv.cardscanner.ui.modules.authorization.registration

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.FragmentData
import com.dimonkiv.cardscanner.ui.modules.authorization.AuthorizationActivity
import com.dimonkiv.cardscanner.utill.FragmentById

class RegistrationFragment: Fragment(), RegistrationContract.Fragment {
    private lateinit var root: View
    private lateinit var presenter: RegistrationPresenter
    private lateinit var view: RegistrationView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_registration, container, false)

        initPresenter()
        iniView()

        return root
    }

    private fun initPresenter() {
        presenter = RegistrationPresenter(this)
    }

    private fun iniView() {
        view = RegistrationView(presenter, root, context!!)
    }

    override fun showPreviousFragment() {
        (activity as AuthorizationActivity).changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
    }
}