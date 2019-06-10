package com.dimonkiv.cardscanner.ui.modules.authorization.login

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.dimonkiv.cardscanner.R

class LoginView(private val presenter: LoginPresenter,
                private val view: View,
                private val context: Context): LoginContract.View {


    private lateinit var loginET: EditText
    private lateinit var passwordET: EditText
    private lateinit var forgotPasswordTV: TextView
    private lateinit var loginBtn: Button
    private lateinit var registerTV: TextView

    init {
        initUI()
        setListeners()
        presenter.setView(this)
    }

    private fun initUI() {
        loginET = view.findViewById(R.id.login_et)
        passwordET = view.findViewById(R.id.password_et)
        loginBtn = view.findViewById(R.id.loginBtn)
        forgotPasswordTV = view.findViewById(R.id.forgotPasswordTV)
        registerTV = view.findViewById(R.id.registerTV)
    }

    private fun setListeners() {
        loginET.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onLoginTextChanged(s.toString())
            }
        })

        passwordET.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onPasswordTextChanged(s.toString())
            }
        })

        loginBtn.setOnClickListener {
            presenter.onLoginButtonClick()
        }

        registerTV.setOnClickListener {
            presenter.onRegisterButtonClick()
        }

        forgotPasswordTV.setOnClickListener {
            presenter.onForgotPasswordButtonClick()
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}