package com.dimonkiv.cardscanner.ui.modules.authorization.registration

class RegistrationPresenter(private val fragment: RegistrationFragment): RegistrationContract.Presenter {
    private lateinit var view: RegistrationView

    private var login = ""
    private var password = ""
    private var reenterPassword = ""

    init {

    }

    override fun setView(view: RegistrationView) {
        this.view = view
    }

    override fun onLoginTextChanged(login: String) {
        this.login = login
    }

    override fun onPasswordTextChanged(password: String) {
        this.password = password
    }

    override fun onReenterPasswordTextChanged(reenterPassword: String) {
        this.reenterPassword = reenterPassword
    }

    override fun onRegistrationButtonClick() {
        if(login.isEmpty()) {
            view.showToast("Введіть логін!")
            return
        }

        if(isLoginValid()) {
            view.showToast("Некоректний логін!")
            return
        }

        if(login.isEmpty()) {
            view.showToast("Введіть пароль!")
            return
        }

        if(isPasswordValid(password)) {
            view.showToast("Пароль має мати не менше 6 символів!")
            return
        }

        if(reenterPassword.isEmpty()) {
            view.showToast("Введіть пароль ще раз!")
            return
        }

        if(isPasswordValid(reenterPassword)) {
            view.showToast("Пароль має мати не менше 6 символів!")
            return
        }

        if(reenterPassword != password) {
            view.showToast("Паролі не співпадають!")
            return
        }

        fragment.showPreviousFragment()
    }

    private fun isLoginValid(): Boolean {
        return !android.util.Patterns.EMAIL_ADDRESS.matcher(login).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        if (password.length < 6)
            return true

        return false
    }
}