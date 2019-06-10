package com.dimonkiv.cardscanner.ui.modules.authorization.registration

interface RegistrationContract {

    interface View {
        fun showToast(message: String)
    }

    interface Presenter {
        fun  setView(view: RegistrationView)

        fun onLoginTextChanged(login: String)

        fun onPasswordTextChanged(password: String)

        fun onReenterPasswordTextChanged(reenterPassword: String)

        fun onRegistrationButtonClick()
    }

    interface Fragment {
        fun showPreviousFragment()
    }
}