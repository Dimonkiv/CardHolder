package com.dimonkiv.cardscanner.ui.modules.authorization.login

interface LoginContract {

    interface View {
        fun showToast(message: String)
    }

    interface Presenter {
        fun setView(view: LoginView)

        fun onLoginTextChanged(login: String)

        fun onPasswordTextChanged(password: String)

        fun onLoginButtonClick()

        fun onForgotPasswordButtonClick()

        fun onRegisterButtonClick()
    }

    interface Fragment {
        fun showRegistrationFragment()

        fun authFinished()
    }
}