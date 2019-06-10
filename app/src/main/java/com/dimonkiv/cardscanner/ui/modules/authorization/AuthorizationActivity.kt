package com.dimonkiv.cardscanner.ui.modules.authorization

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.dimonkiv.cardscanner.R
import com.dimonkiv.cardscanner.data.model.FragmentData
import com.dimonkiv.cardscanner.ui.modules.authorization.login.LoginFragment
import com.dimonkiv.cardscanner.ui.modules.authorization.registration.RegistrationFragment
import com.dimonkiv.cardscanner.ui.modules.main.MainActivity
import com.dimonkiv.cardscanner.utill.FragmentById.*

class AuthorizationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        changeFragment(FragmentData(LOGIN_FRAGMENT))
    }

    fun changeFragment(fragmentData: FragmentData) {
        val fragmentById = fragmentData.getFragmentById()

        when(fragmentById) {

            LOGIN_FRAGMENT -> addToContainer(LoginFragment(), null)

            REGISTRATION_FRAGMENT -> addToContainer(RegistrationFragment(), null)

            BACK_FRAGMENT -> onBackPressed()

            AUTH_FINISHED -> showMainActivity()
        }
    }

    private fun showMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun addToContainer(fragment: Fragment, bundle: Bundle?) {
        if(bundle != null) fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
    }
}
