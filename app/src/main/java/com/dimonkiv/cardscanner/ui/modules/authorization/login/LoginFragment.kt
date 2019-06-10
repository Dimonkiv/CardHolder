package com.dimonkiv.cardscanner.ui.modules.authorization.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.FragmentData
import com.dimonkiv.cardscanner.ui.modules.authorization.AuthorizationActivity
import com.dimonkiv.cardscanner.utill.FragmentById

class LoginFragment: Fragment(), LoginContract.Fragment {
    private lateinit var root: View
    private lateinit var presenter: LoginPresenter
    private lateinit var view: LoginView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_login, container, false)

        initPresenter()
        initView()

        return root
    }

    private fun initPresenter() {
        presenter = LoginPresenter(this)
    }

    private fun initView() {
        view = LoginView(presenter, root, context!!)
    }

    override fun showRegistrationFragment() {
        (activity as AuthorizationActivity).changeFragment(FragmentData(FragmentById.REGISTRATION_FRAGMENT))
    }

    override fun authFinished() {
        (activity as AuthorizationActivity).changeFragment(FragmentData(FragmentById.AUTH_FINISHED))
    }
}