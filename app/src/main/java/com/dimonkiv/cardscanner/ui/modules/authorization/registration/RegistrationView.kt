package com.dimonkiv.cardscanner.ui.modules.authorization.registration

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.dimonkiv.cardscanner.R

class RegistrationView(private val presenter: RegistrationPresenter,
                       private val view: View,
                       private val context: Context): RegistrationContract.View {

    private lateinit var loginET: EditText
    private lateinit var passwordET: EditText
    private lateinit var reenterPasswordET: EditText
    private lateinit var registrationBtn: Button

    init {
        presenter.setView(this)
    }

    init {
        initUI()
        setListeners()
        presenter.setView(this)
    }

    private fun initUI() {
        loginET = view.findViewById(R.id.login_et)
        passwordET = view.findViewById(R.id.password_et)
        reenterPasswordET = view.findViewById(R.id.reenter_password_et)
        registrationBtn = view.findViewById(R.id.registrationBtn)
    }

    private fun setListeners() {
        loginET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onLoginTextChanged(s.toString())
            }
        })

        passwordET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onPasswordTextChanged(s.toString())
            }
        })

        reenterPasswordET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onReenterPasswordTextChanged(s.toString())
            }
        })

        registrationBtn.setOnClickListener {
            presenter.onRegistrationButtonClick()
        }

    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}